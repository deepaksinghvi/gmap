package com.ms.gmap.bid.service;

import com.ms.gmap.bid.domain.Bid;

public interface BidService {

  Bid createBid(Bid bid);

  Bid getBid(Long tenantId, Long bidId);

  Bid getBid(Long id);

  void deleteBid(Long id);

  Bid updateBid(Bid bid);
}
