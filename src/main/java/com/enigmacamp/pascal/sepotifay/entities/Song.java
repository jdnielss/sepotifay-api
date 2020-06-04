package com.enigmacamp.pascal.sepotifay.entities;

import com.enigmacamp.pascal.sepotifay.constants.SongMessageConstant;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Entity
@Table(name = "songs")
@Getter @Setter @EqualsAndHashCode
public class Song {
  @Id
  @GenericGenerator(name = "sid", strategy = "uuid2")
  @GeneratedValue(generator = "sid", strategy = GenerationType.IDENTITY)
  private String id;

  @Column(nullable = false)
  @NotEmpty(message = SongMessageConstant.VALIDATION_TITLE_NOT_EMPTY)
  private String title;

  @Column(nullable = false)
  @NotEmpty(message = SongMessageConstant.VALIDATION_YEAR_NOT_EMPTY)
  private Integer releaseYear;

  @Column(nullable = false)
  @NotEmpty(message = SongMessageConstant.VALIDATION_DURATION_NOT_EMPTY)
  private Integer duration;

  @JsonIgnore
  @ManyToOne
  @JoinColumn(name = "artist_id")
  private Artist artist;

  @JsonIgnore
  @ManyToOne
  private Album album;

  @JsonIgnore
  @ManyToOne
  private Genre genre;

  @JsonIgnore
  @ManyToMany(mappedBy = "songs")
  private List<Playlist> playlists;

  @OneToMany(mappedBy = "song")
  private List<TransactionHistory> transactions;

  @Transient
  private String artistId;
}
