package com.enigmacamp.pascal.sepotifay.repositories;

import com.enigmacamp.pascal.sepotifay.entities.Artist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArtistRepository extends JpaRepository<Artist, String> {
}
