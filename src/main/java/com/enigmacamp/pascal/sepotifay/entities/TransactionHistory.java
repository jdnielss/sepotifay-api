package com.enigmacamp.pascal.sepotifay.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "transactions")
@Getter @Setter @EqualsAndHashCode
public class TransactionHistory {
  @Id
  @GenericGenerator(name = "txid", strategy = "uuid2")
  @GeneratedValue(generator = "txid", strategy = GenerationType.IDENTITY)
  private String id;

  @Temporal(TemporalType.TIMESTAMP)
  @Column(nullable = false)
  private Date trxDate;

  @Column(nullable = false)
  private Double amount;

  @Column
  private Double albumDiscount;

  @ManyToOne
  @JoinColumn(name = "song_id", nullable = false)
  private Song song;

  @ManyToOne
  @JoinColumn(name = "wallet_id", nullable = false)
  private Wallet wallet;
}
