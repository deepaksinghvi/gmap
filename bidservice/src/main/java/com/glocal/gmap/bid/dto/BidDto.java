package com.glocal.gmap.bid.dto;

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

  private TenantDto tenant;

  private String bidstatus;
}
