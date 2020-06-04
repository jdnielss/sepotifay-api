package com.enigmacamp.pascal.sepotifay.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "wallets")
@Getter @Setter @EqualsAndHashCode
public class Wallet {
  @Id
  @GenericGenerator(name = "wid", strategy = "uuid2")
  @GeneratedValue(generator = "wid", strategy = GenerationType.IDENTITY)
  private String id;

  @Column
  private Double balance;

  @OneToOne
  @JoinColumn(name = "owner_id")
  private Account owner;

  @OneToMany(mappedBy = "wallet")
  private List<WalletHistory> histories;

  @OneToMany(mappedBy = "wallet")
  private List<TransactionHistory> transactions;
}
