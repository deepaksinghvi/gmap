package com.ms.gmap.bid.dto;

import com.ms.gmap.common.dto.TenantDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BidDto {

  private Long id;

  private TenantDto tenant;

  private String bidstatus;

  private String bidname;

  private String bidwinner;
}
