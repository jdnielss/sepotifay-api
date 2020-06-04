package com.enigmacamp.pascal.sepotifay.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "playlists")
@Getter @Setter @EqualsAndHashCode
public class Playlist {

  @Id
  @GenericGenerator(name = "plid", strategy = "uuid2")
  @GeneratedValue(generator = "plid", strategy = GenerationType.IDENTITY)
  private String id;

  @Column(nullable = false)
  private String name;

  @Column(nullable = false, columnDefinition = "boolean default true")
  private Boolean isPublic;

  @ManyToOne
  private Account author;

  @ManyToMany
  @JoinTable(name = "playlists_songs",
      joinColumns = @JoinColumn(name = "playlist_id", nullable = false, updatable = false),
      inverseJoinColumns = @JoinColumn(name = "song_id", nullable = false, updatable = false)
  )
  private List<Song> songs;
}
