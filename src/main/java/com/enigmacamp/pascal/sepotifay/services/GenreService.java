package com.enigmacamp.pascal.sepotifay.services;

import com.enigmacamp.pascal.sepotifay.entities.Genre;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface GenreService extends Service<Genre> {
  Page<Genre> search(String keyword, Pageable pager);
}
