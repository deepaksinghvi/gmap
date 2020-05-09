package com.ms.gmap.common.repository;

import com.ms.gmap.common.domain.Tenant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TenantRepository  extends JpaRepository<Tenant, Long>,
    PagingAndSortingRepository<Tenant, Long> {

}
