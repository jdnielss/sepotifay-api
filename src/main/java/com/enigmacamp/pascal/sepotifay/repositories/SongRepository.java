package com.enigmacamp.pascal.sepotifay.repositories;

import com.enigmacamp.pascal.sepotifay.entities.Song;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SongRepository extends JpaRepository<Song, String> {
}
