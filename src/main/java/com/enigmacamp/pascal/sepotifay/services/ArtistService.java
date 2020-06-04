package com.enigmacamp.pascal.sepotifay.services;

import com.enigmacamp.pascal.sepotifay.entities.Artist;
import org.springframework.web.multipart.MultipartFile;

public interface ArtistService extends Service<Artist> {
  void create(Artist entity, MultipartFile file);
}
