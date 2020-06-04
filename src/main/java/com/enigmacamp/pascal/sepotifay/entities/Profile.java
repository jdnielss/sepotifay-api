package com.enigmacamp.pascal.sepotifay.entities;

import com.enigmacamp.pascal.sepotifay.enums.Gender;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "profiles")
@Getter @Setter @EqualsAndHashCode
public class Profile {

  @Id
  @GenericGenerator(name = "pid", strategy = "uuid2")
  @GeneratedValue(generator = "pid", strategy = GenerationType.IDENTITY)
  private String id;

  @Column(nullable = false)
  private String firstName;

  @Column
  private String middleName;

  @Column
  private String lastName;

  @Column
  private Gender gender;

  @Column(unique = true, nullable = false)
  private String email;

  @Column
  private String phone;

  @Temporal(value = TemporalType.DATE)
  @Column
  private Date birthDate;

  @Column
  private String location;

  @OneToOne(mappedBy = "profile")
  private Account account;
}
