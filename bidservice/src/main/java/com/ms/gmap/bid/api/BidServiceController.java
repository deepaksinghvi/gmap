package com.ms.gmap.bid.api;

import com.ms.gmap.bid.domain.Bid;
import com.ms.gmap.bid.dto.BidDto;
import com.ms.gmap.bid.service.BidService;
import java.text.ParseException;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class BidServiceController {

  @Autowired
  private ModelMapper modelMapper;

  @Autowired
  private BidService bidService;


  @RequestMapping(method = RequestMethod.POST, path = "/bid", consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<BidDto> createbid(@RequestBody BidDto bidDto) {
    Bid bid= null;
    try {
      bid = bidService.createBid(convertToEntity(bidDto));
    } catch (ParseException e) {
      log.error(e.getMessage(), e);
    }
    return new ResponseEntity(convertToDto(bid), HttpStatus.CREATED);
  }

  @RequestMapping(method = RequestMethod.PUT, path = "/bid", consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<BidDto> updatebid(@RequestBody BidDto bidDto) {
    Bid bid = bidService.getBid(bidDto.getTenant().getId(), bidDto.getId());
    bid.setBidstatus(bidDto.getBidstatus());
    bid.setBidwinner(bidDto.getBidwinner());
    bid = bidService.updateBid(bid);
    return new ResponseEntity(convertToDto(bid), HttpStatus.CREATED);
  }


  @RequestMapping(method = RequestMethod.GET, value = "/bid/{bidId}", produces = MediaType.APPLICATION_JSON_VALUE)
  public @ResponseBody
  ResponseEntity<BidDto> getbid(@PathVariable Long bidId) {
   return new ResponseEntity(convertToDto(bidService.getBid(bidId)), HttpStatus.OK);
  }

  private BidDto convertToDto(Bid bid) {
    return modelMapper.map(bid, BidDto.class);
  }

  private Bid convertToEntity(BidDto bidDto) throws ParseException {
    return modelMapper.map(bidDto, Bid.class);
  }

}
