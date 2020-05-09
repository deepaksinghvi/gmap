package com.ms.gmap.bid.repository;

import com.ms.gmap.bid.domain.Bid;
import java.math.BigInteger;
import java.util.List;
import java.util.Optional;
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

  @Query("SELECT b FROM Bid b JOIN FETCH b.tenant t WHERE t.id = :tenantId AND b.id = :bidId")
  Optional<Bid> findBid(@Param("tenantId") Long tenantId, @Param("bidId") Long bidId);

}
