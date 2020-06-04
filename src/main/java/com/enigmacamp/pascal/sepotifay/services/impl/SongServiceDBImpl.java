package com.enigmacamp.pascal.sepotifay.services.impl;

import com.enigmacamp.pascal.sepotifay.entities.Song;
import com.enigmacamp.pascal.sepotifay.repositories.SongRepository;
import com.enigmacamp.pascal.sepotifay.services.SongService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SongServiceDBImpl implements SongService {
  private final SongRepository songRepository;

  public SongServiceDBImpl(SongRepository songRepository) {
    this.songRepository = songRepository;
  }

  @Override
  public void create(Song song) {
    songRepository.save(song);
  }

  @Override
  public void update(Song song) {

  }

  @Override
  public void delete(String id) {

  }

  @Override
  public Song getById(String id) {
    return songRepository.findById(id).orElse(null);
  }

  @Override
  public Page<Song> getAll(Pageable pager) {
    return songRepository.findAll(pager);
  }
}
