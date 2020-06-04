package com.enigmacamp.pascal.sepotifay.entities;

import com.enigmacamp.pascal.sepotifay.enums.HistoryType;
import com.enigmacamp.pascal.sepotifay.validators.constraints.ValidHistoryType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "wallet_histories")
@Getter @Setter @EqualsAndHashCode
public class WalletHistory {
  @Id
  @GenericGenerator(name = "whid", strategy = "uuid2")
  @GeneratedValue(generator = "whid", strategy = GenerationType.IDENTITY)
  private String id;

  @Column(name = "history_type", nullable = false)
  @ValidHistoryType(anyOf = { HistoryType.PAYMENT, HistoryType.TOPUP, HistoryType.WITHDRAWAL })
  private HistoryType type;

  @Column
  private Double amount;

  @Temporal(value = TemporalType.TIMESTAMP)
  @Column
  private Date trxDate;

  @JsonIgnore
  @ManyToOne
  @JoinColumn(name = "wallet_id", nullable = false)
  private Wallet wallet;
}
