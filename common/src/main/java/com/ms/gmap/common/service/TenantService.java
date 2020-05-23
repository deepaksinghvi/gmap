package com.ms.gmap.common.service;

import com.ms.gmap.common.domain.Tenant;

public interface TenantService {
  Tenant onBoardTenant(Tenant tenant);

  Tenant getTenant(Long id);

  Tenant getTenant(String id);

  void deleteTenant(Long id);

  Tenant updateTenant(Tenant bid);
}
