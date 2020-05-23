package com.ms.gmap.user.api;

import com.ms.gmap.common.domain.Tenant;
import com.ms.gmap.common.dto.TenantDto;
import com.ms.gmap.common.service.TenantService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class TenantController {

  @Autowired
  private ModelMapper modelMapper;

  @Autowired
  private TenantService tenantService;

  @PostMapping(value = "/tenant", consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<TenantDto> onboardTenant(@RequestBody TenantDto tenantDto) {
    return new ResponseEntity(convertToDto(tenantService.onBoardTenant(convertToEntity(tenantDto))), HttpStatus.CREATED);
  }

  @GetMapping(value = "/tenant/{tenantId}", produces = MediaType.APPLICATION_JSON_VALUE)
  public @ResponseBody ResponseEntity<TenantDto> getTenant(@PathVariable String tenantId) {
    return new ResponseEntity(convertToDto(tenantService.getTenant(tenantId)), HttpStatus.OK);
  }

  private TenantDto convertToDto(Tenant tenant) {
    return modelMapper.map(tenant, TenantDto.class);
  }

  private Tenant convertToEntity(TenantDto tenantDto) {
    return modelMapper.map(tenantDto, Tenant.class);
  }
}
