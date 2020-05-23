package com.ms.gmap.user.dto;

import com.ms.gmap.common.dto.TenantDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

  private Long id;

  private TenantDto tenant;

  private String userid;

  private String name;

  private String email;
}
