package com.ms.gmap.user.domain;

import com.ms.gmap.common.domain.Tenant;
import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Data
@EntityListeners(AuditingEntityListener.class)
@Table(name="user")
public class User {
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_generator")
  @SequenceGenerator(name="user_generator", sequenceName = "user_seq", allocationSize = 100)
  @Column(name = "id", unique=true, updatable = false, nullable=false, columnDefinition = "bigint")
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name="tenant_id", referencedColumnName = "tenant_id")
  private Tenant tenant;

  @Column(name = "userid", length=32)
  private String userid;

  @Column(name = "email", length=32)
  private String email;

  @CreatedDate
  @Column(name="time_created")
  private Timestamp timeCreated;

  @LastModifiedDate
  @Column(name="time_updated")
  private Timestamp timeUpdated;

  @Version
  private int version;
}
