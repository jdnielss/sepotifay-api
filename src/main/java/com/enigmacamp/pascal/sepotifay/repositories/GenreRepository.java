package com.enigmacamp.pascal.sepotifay.repositories;

import com.enigmacamp.pascal.sepotifay.entities.Genre;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface GenreRepository extends JpaRepository<Genre, String>, PagingAndSortingRepository<Genre, String> {
  Page<Genre> findAllByNameContains(String name, Pageable pageable);
}
