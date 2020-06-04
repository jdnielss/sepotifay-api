package com.enigmacamp.pascal.sepotifay.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.List;

import static com.enigmacamp.pascal.sepotifay.constants.GenreMessagesConstant.VALIDATION_NOT_EMPTY;

@Entity
@Table(name = "genres")
@Getter @Setter @EqualsAndHashCode
public class Genre {

  @Id
  @GenericGenerator(name = "gid", strategy = "uuid2")
  @GeneratedValue(generator = "gid", strategy = GenerationType.IDENTITY)
  private String id;

  @NotEmpty(message = VALIDATION_NOT_EMPTY)
  @Column(nullable = false)
  private String name;

  @OneToMany(mappedBy = "genre")
  private List<Song> songs;
}
