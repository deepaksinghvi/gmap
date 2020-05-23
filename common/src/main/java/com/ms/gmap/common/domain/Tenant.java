package com.ms.gmap.common.domain;


import java.io.Serializable;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Version;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Data
@Table(name = "tenant")
public class Tenant implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tenant_generator")
  @SequenceGenerator(name="tenant_generator", sequenceName = "tenant_id_seq", allocationSize = 1)
  @Column(name = "id", unique=true, updatable = false, nullable=false, columnDefinition = "bigint")
  private Long id;

  @Column(name="tenant_id", length=25, unique = true, nullable = false)
  private String tenantId;

  @Column(name="organization_name", length=255)
  private String organizationName;

  @Column(name="realm", length = 50)
  private String realm;

  @Column(name = "tenant_config", length = 1000)
  private String tenantConfig;

  @Column(name = "locked")
  private Boolean locked;

  @CreatedDate
  @Column(name="time_created")
  private Timestamp timeCreated;

  @LastModifiedDate
  @Column(name="time_updated")
  private Timestamp timeUpdated;

  @Version
  private int version;

  @Override
  public boolean equals(Object o) {
    if (o == this) {
      return true;
    }
    if (!(o instanceof Tenant)) {
      return false;
    }
    Tenant t = (Tenant)o;
    return tenantId.equals(t.tenantId);
  }

}
