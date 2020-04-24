package com.glocal.gmap.bid.service;

import com.glocal.gmap.bid.domain.Bid;

public interface BidService {

  Bid createBid(Bid bid);

  Bid getBid(Long id);

  void deleteBid(Long id);

  Bid updateBid(Bid bid);
}
