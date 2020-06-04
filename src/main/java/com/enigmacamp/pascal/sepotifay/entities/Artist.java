package com.enigmacamp.pascal.sepotifay.entities;

import com.enigmacamp.pascal.sepotifay.constants.ArtistMessageConstant;
import com.enigmacamp.pascal.sepotifay.enums.Gender;
import com.enigmacamp.pascal.sepotifay.validators.constraints.ValidGender;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Entity
@Table(name = "artists")
@Getter @Setter @EqualsAndHashCode
public class Artist {
  @Id
  @GenericGenerator(name = "arid", strategy = "uuid2")
  @GeneratedValue(generator = "arid", strategy = GenerationType.IDENTITY)
  private String id;

  @NotEmpty(message = ArtistMessageConstant.VALIDATION_NAME_NOT_EMPTY)
  @Column(nullable = false)
  private String name;

  @ValidGender(anyOf = { Gender.MALE, Gender.FEMALE })
  @Column(nullable = false)
  private Gender gender;

  @NotEmpty(message = ArtistMessageConstant.VALIDATION_BIOGRAPHY_NOT_EMPTY)
  @Column(nullable = false)
  private String biography;

  @Column(nullable = false)
  private String photo;

  @NotEmpty(message = ArtistMessageConstant.VALIDATION_DEBUT_YEAR_NOT_EMPTY)
  @Column(nullable = false)
  private Integer debutYear;

  @OneToMany(mappedBy = "artist")
  private List<Song> songs;
}
