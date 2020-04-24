package com.glocal.gmap.bid.service.impl;

import com.glocal.gmap.bid.domain.Bid;
import com.glocal.gmap.bid.repository.BidRepository;
import com.glocal.gmap.bid.service.BidService;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BidServiceImpl implements BidService {

  @Autowired
  private BidRepository repository;

  @Override
  public Bid createBid(Bid bid) {
    return repository.save(bid);
  }

  @Override
  public Bid getBid(Long id) {
    Optional<Bid> result = repository.findById(id);
    return result.isPresent()?result.get():null;
  }

  @Override
  public void deleteBid(Long id) {
     repository.deleteById(id);
  }

  @Override
  public Bid updateBid(Bid bid) {
    return repository.save(bid);
  }
}
