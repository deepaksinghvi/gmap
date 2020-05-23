package com.ms.gmap.common.dto;

import java.math.BigInteger;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TenantDto {

  private Long id;

  private String tenantId;

  private String organizationName;

  private String realm;

  private String tenantConfig;

  private Boolean locked;
}
