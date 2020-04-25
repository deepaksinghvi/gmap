package com.glocal.gmap.bid.repository;

import com.glocal.gmap.bid.domain.Bid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BidRepository extends JpaRepository<Bid, Long>,
    PagingAndSortingRepository<Bid, Long> {

  /*@Query("select b from bid b where b.bidstatus=:status")
  Page<Bid> findByStatus(@Param("status") String status, Pageable pageReq);
  default Page<Bid> findByStatus(Bid bid, Pageable pageReq) {
    return findByStatus(bid.getBidstatus(), pageReq);
  }*/

}
