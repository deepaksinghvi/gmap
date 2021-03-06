package com.ms.gmap.common.service.impl;

import com.ms.gmap.common.domain.Tenant;
import com.ms.gmap.common.repository.TenantRepository;
import com.ms.gmap.common.service.TenantService;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TenantServiceImpl implements TenantService {

  @Autowired
  private TenantRepository tenantRepository;

  @Override
  public Tenant onBoardTenant(Tenant tenant) {
    return tenantRepository.save(tenant);
  }

  @Override
  public Tenant getTenant(Long id) {
    Optional<Tenant> result = tenantRepository.findById(id);
    return result.isPresent()?result.get():null;
  }

  @Override
  public Tenant getTenant(String tenantId) {
    Optional<Tenant> result = tenantRepository.findTenant(tenantId);
    return result.isPresent()?result.get():null;
  }

  @Override
  public void deleteTenant(Long id) {
    tenantRepository.deleteById(id);
  }

  @Override
  public Tenant updateTenant(Tenant bid) {
    return tenantRepository.save(bid);
  }
}
