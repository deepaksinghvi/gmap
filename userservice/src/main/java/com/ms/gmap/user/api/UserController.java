package com.ms.gmap.user.api;

import com.ms.gmap.common.domain.Tenant;
import com.ms.gmap.common.dto.TenantDto;
import com.ms.gmap.common.service.TenantService;
import com.ms.gmap.user.domain.User;
import com.ms.gmap.user.dto.UserDTO;
import com.ms.gmap.user.service.UserService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import java.text.ParseException;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@Slf4j
public class UserController {

  @Autowired
  private ModelMapper modelMapper;

  @Autowired
  private UserService userService;

  @Autowired
  private TenantService tenantService;

  private static final String USER_SERVICE = "userservice";


  @Value("${gmap.bidservice.url}")
  private String bidServiceUrl;

  @Autowired
  private RestTemplate restTemplate;


  @PostMapping("/user")
  public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO) {
    User user = null;
    try {
      // not sending tenant to call tenant api and get the tenant.
      if(null == userDTO.getTenant().getRealm()) {
        ResponseEntity<TenantDto> tenantDtoResponseEntity = restTemplate.
            getForEntity(bidServiceUrl + "/tenant/" + userDTO.getTenant().getTenantId(),
                TenantDto.class);
        userDTO.setTenant(tenantDtoResponseEntity.getBody());
      }
      user = userService.createUser(convertToEntity(userDTO));
    } catch (ParseException e) {
      log.error(e.getMessage(), e);
    }
    return new ResponseEntity(convertToDto(user), HttpStatus.CREATED);
  }

  @PutMapping("/user")
  public ResponseEntity<UserDTO> updateUser(@RequestBody UserDTO userDTO) {
    User user = userService.getUser(userDTO.getTenant().getTenantId(), userDTO.getUserid());
    user.setEmail(userDTO.getEmail());
    return new ResponseEntity(convertToDto(user), HttpStatus.OK);
  }


  @GetMapping("/user")
  public @ResponseBody
  ResponseEntity<UserDTO> getUser(@PathVariable String tenantId, @PathVariable String userId) {
    return new ResponseEntity(convertToDto(userService.getUser(tenantId, userId)), HttpStatus.OK);
  }

  @GetMapping("/user/tenant/{tenantId}")
  @ResponseStatus(HttpStatus.OK)
  @CircuitBreaker(name = USER_SERVICE, fallbackMethod="fallBack")
  public @ResponseBody ResponseEntity<TenantDto> getTenant(@PathVariable("tenantId") String tenantId) {
    return restTemplate.
        getForEntity(bidServiceUrl + "/tenant/" + tenantId, TenantDto.class);
  }

  private  ResponseEntity<String> fallBack(String tenantId, Exception e){
    log.error(e.getMessage(),e);
    return new ResponseEntity<String>("Error executing remote service", HttpStatus.INTERNAL_SERVER_ERROR);
  }

  private UserDTO convertToDto(User user) {
    return modelMapper.map(user, UserDTO.class);
  }

  private User convertToEntity(UserDTO userDTO) throws ParseException {
    return modelMapper.map(userDTO, User.class);
  }

  private TenantDto convertToDto(Tenant tenant) {
    return modelMapper.map(tenant, TenantDto.class);
  }
}
