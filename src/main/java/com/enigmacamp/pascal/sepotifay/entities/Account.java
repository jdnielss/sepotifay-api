package com.enigmacamp.pascal.sepotifay.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "accounts")
@Getter @Setter @EqualsAndHashCode
public class Account {
  @Id
  @GenericGenerator(name = "acid", strategy = "uuid2")
  @GeneratedValue(generator = "acid", strategy = GenerationType.IDENTITY)
  private String id;

  @Column(nullable = false, columnDefinition = "boolean default true")
  private Boolean isActive;

  @OneToOne
  @JoinColumn(name = "profile_id")
  @JsonIgnore
  private Profile profile;

  @OneToOne(mappedBy = "owner")
  @JsonIgnore
  private Wallet wallet;
}
