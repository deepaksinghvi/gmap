package com.ms.gmap.common.repository;

import com.ms.gmap.common.domain.Tenant;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TenantRepository  extends JpaRepository<Tenant, Long>, PagingAndSortingRepository<Tenant, Long> {

  @Query("SELECT t FROM Tenant t WHERE t.tenantId =:tenantId")
  Optional<Tenant> findTenant(@Param("tenantId") String tenantId);

}
