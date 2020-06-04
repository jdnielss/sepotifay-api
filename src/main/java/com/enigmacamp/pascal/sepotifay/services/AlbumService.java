package com.enigmacamp.pascal.sepotifay.services;

import com.enigmacamp.pascal.sepotifay.entities.Album;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AlbumService extends Service<Album> {
  Page<Album> search(String keyword, Pageable pageable);
}
