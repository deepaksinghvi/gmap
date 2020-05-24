package com.ms.gmap.bid.api;

import com.ms.gmap.bid.domain.Bid;
import com.ms.gmap.bid.dto.BidDto;
import com.ms.gmap.bid.service.BidService;
import com.ms.gmap.common.dto.TenantDto;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.Retry;
import io.github.resilience4j.retry.RetryConfig;
import io.github.resilience4j.retry.RetryRegistry;
import java.net.ConnectException;
import java.text.ParseException;
import java.time.Duration;
import java.util.function.Supplier;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

@RestController
@Slf4j
public class BidController {

  @Autowired
  private ModelMapper modelMapper;

  @Autowired
  private BidService bidService;



  private static final String BID_SERVICE = "bidservice";


  @Value("${gmap.userservice.url}")
  private String userServiceUrl;

  @Autowired
  private RestTemplate restTemplate;


  @RequestMapping(method = RequestMethod.POST, path = "/bid", consumes = MediaType.APPLICATION_JSON_VALUE)
  @CircuitBreaker(name = BID_SERVICE, fallbackMethod="tenantRetrivalFallBack")
  public ResponseEntity<BidDto> createbid(@RequestBody BidDto bidDto) {
    Bid bid= null;
    try {
      // not sending tenant to call tenant api and get the tenant from the api call from userservice.
      if(null == bidDto.getTenant().getRealm()) {

        /*
        From the documentation of Resilience4j
        The Resilience4j Aspects order is following:
        Retry ( CircuitBreaker ( RateLimiter ( TimeLimiter ( Bulkhead ( Function ) ) ) ) ) so Retry is applied at the end (if needed).
        If you need a different order, you must use the functional chaining style instead of the Spring annotations style.
        So instead of using Resilience4j of springboot, using the functional execution of retry method.
         */
        RetryConfig config = RetryConfig.custom().maxAttempts(3).waitDuration(Duration.ofSeconds(10))
            .retryOnResult(response -> response.equals(null))
            .retryOnException(e -> e instanceof Exception).build();
        RetryRegistry registry = RetryRegistry.of(config);
        Supplier<TenantDto> supplier = () -> this.getTenantDTO(bidDto);
        Retry retry = registry.retry(BID_SERVICE);
        TenantDto tenantDto = retry.executeSupplier(supplier);
        log.info("tenantDto: {}",tenantDto);
        bidDto.setTenant(tenantDto);
      }
      bid = bidService.createBid(convertToEntity(bidDto));
    } catch (ParseException e) {
      log.error(e.getMessage(), e);
    }
    return new ResponseEntity(convertToDto(bid), HttpStatus.CREATED);
  }

  private TenantDto getTenantDTO(@RequestBody BidDto bidDto) {
    log.info("getTenantDTO called");
    ResponseEntity<TenantDto> responseEntity = restTemplate.
              getForEntity(userServiceUrl + "/tenant/" + bidDto.getTenant().getTenantId(),
                  TenantDto.class);
    return responseEntity.getBody();

  }

  @RequestMapping(method = RequestMethod.PUT, path = "/bid", consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<BidDto> updatebid(@RequestBody BidDto bidDto) {
    Bid bid = bidService.getBid(bidDto.getTenant().getId(), bidDto.getId());
    bid.setBidstatus(bidDto.getBidstatus());
    bid.setBidwinner(bidDto.getBidwinner());
    bid = bidService.updateBid(bid);
    return new ResponseEntity(convertToDto(bid), HttpStatus.OK);
  }


  @RequestMapping(method = RequestMethod.GET, value = "/bid/{bidId}", produces = MediaType.APPLICATION_JSON_VALUE)
  public @ResponseBody ResponseEntity<BidDto> getbid(@PathVariable Long bidId) {
   return new ResponseEntity(convertToDto(bidService.getBid(bidId)), HttpStatus.OK);
  }

  private BidDto convertToDto(Bid bid) {
    return modelMapper.map(bid, BidDto.class);
  }

  private Bid convertToEntity(BidDto bidDto) throws ParseException {
    return modelMapper.map(bidDto, Bid.class);
  }

  private  ResponseEntity<String> tenantRetrivalFallBack(BidDto bidDto, Exception e){
    log.error(e.getMessage(),e);
    return new ResponseEntity<String>("Error executing Remote User Service", HttpStatus.INTERNAL_SERVER_ERROR);
  }

  private  ResponseEntity<String> tenantRetryFallBack(BidDto bidDto, Exception e){
    log.error(e.getMessage(),e);
    return new ResponseEntity<String>("Exausted all retries", HttpStatus.INTERNAL_SERVER_ERROR);
  }

}
