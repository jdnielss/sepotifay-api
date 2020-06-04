package com.enigmacamp.pascal.sepotifay.entities;

import com.enigmacamp.pascal.sepotifay.constants.AlbumMessagesConstant;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Entity
@Table(name = "albums")
@Getter @Setter @EqualsAndHashCode
public class Album {
  public Album() {}
  public Album(String title, String description) {
    this.title = title;
    this.description = description;
  }

  @Id
  @GenericGenerator(name = "alid", strategy = "uuid2")
  @GeneratedValue(generator = "alid", strategy = GenerationType.IDENTITY)
  private String id;

  @NotEmpty(message = AlbumMessagesConstant.VALIDATION_TITLE_NOT_EMPTY)
  @Column(nullable = false)
  private String title;

  @Column
  private String description;

  @NotEmpty(message = AlbumMessagesConstant.VALIDATION_RELEASE_YEAR_NOT_EMPTY)
  @Column(nullable = false)
  private Integer releaseYear;

  @Column(columnDefinition = "double default 0.0")
  private Double discount;

  @NotEmpty(message = AlbumMessagesConstant.VALIDATION_IMAGE_NOT_EMPTY)
  @Column(nullable = false)
  private String image;

  @OneToMany(mappedBy = "album")
  private List<Song> songs;
}
