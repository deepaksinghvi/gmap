package com.glocal.gmap.bid.dto;

import com.glocal.gmap.bid.domain.Tenant;
import java.math.BigInteger;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BidDto {

  private BigInteger id;

  private Tenant tenant;

  private String bidstatus;
}
