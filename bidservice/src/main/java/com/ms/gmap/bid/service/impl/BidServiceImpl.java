package com.ms.gmap.bid.service.impl;

import com.ms.gmap.bid.domain.Bid;
import com.ms.gmap.bid.repository.BidRepository;
import com.ms.gmap.bid.service.BidService;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BidServiceImpl implements BidService {

  @Autowired
  private BidRepository bidRepository;

  @Override
  public Bid createBid(Bid bid) {
    return bidRepository.save(bid);
  }

  @Override
  public Bid getBid(Long tenantId, Long bidId) {
    Optional<Bid> result = bidRepository.findBid(tenantId, bidId);
    return result.isPresent()?result.get():null;
  }

  @Override
  public Bid getBid(Long id) {
    Optional<Bid> result = bidRepository.findById(id);
    return result.isPresent()?result.get():null;
  }

  @Override
  public void deleteBid(Long id) {
    bidRepository.deleteById(id);
  }

  @Override
  public Bid updateBid(Bid bid) {
    return bidRepository.save(bid);
  }
}
