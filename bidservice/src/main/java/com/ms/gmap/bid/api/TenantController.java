package com.ms.gmap.bid.api;

import com.ms.gmap.common.domain.Tenant;
import com.ms.gmap.bid.dto.BidDto;
import com.ms.gmap.common.dto.TenantDto;
import com.ms.gmap.common.service.TenantService;
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
public class TenantController {

  @Autowired
  private ModelMapper modelMapper;

  @Autowired
  private TenantService tenantService;



  @RequestMapping(method = RequestMethod.POST, path = "/tenant", consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<BidDto> onboardTenant(@RequestBody TenantDto tenantDto) {
    Tenant tenant= null;
    try {
      tenant = tenantService.onBoardTenant(convertToEntity(tenantDto));
    } catch (ParseException e) {
      log.error(e.getMessage(), e);
    }
    return new ResponseEntity(tenant, HttpStatus.CREATED);
  }


  @RequestMapping(method = RequestMethod.GET, value = "/tenant/{tenantId}", produces = MediaType.APPLICATION_JSON_VALUE)
  public @ResponseBody
  ResponseEntity<TenantDto> getTenant(@PathVariable Long tenantId) {

    return new ResponseEntity(convertToDto(tenantService.getTenant(tenantId)), HttpStatus.OK);
  }

  private TenantDto convertToDto(Tenant tenant) {
    return modelMapper.map(tenant, TenantDto.class);
  }

  private Tenant convertToEntity(TenantDto tenantDto) throws ParseException {
    return modelMapper.map(tenantDto, Tenant.class);
  }
}
