package com.glocal.gmap.bid.domain;

import com.fasterxml.jackson.databind.JsonNode;
import java.math.BigInteger;
import java.sql.Timestamp;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
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
@Table(name="bid")
public class Bid {
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bid_generator")
  @SequenceGenerator(name="bid_generator", sequenceName = "bid_seq", allocationSize = 100)
  @Column(name = "id", unique=true, updatable = false, nullable=false, columnDefinition = "bigint")
  private BigInteger id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name="tenant_id", referencedColumnName = "tenant_id")
  private Tenant tenant;

  @Column(name = "bidstatus", length=32)
  private String bidstatus;

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
    if (!(o instanceof Bid)) {
      return false;
    }
    Bid bid = (Bid)o;
    return id.equals(bid.id);
  }

}
