package com.ms.gmap.bid.service;

import com.ms.gmap.common.domain.Tenant;

public interface TenantService {
  Tenant onBoardTenant(Tenant tenant);

  Tenant getTenant(Long id);

  void deleteTenant(Long id);

  Tenant updateTenant(Tenant bid);
}
