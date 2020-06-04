package com.enigmacamp.pascal.sepotifay.services.impl;

import com.enigmacamp.pascal.sepotifay.entities.Genre;
import com.enigmacamp.pascal.sepotifay.exceptions.ResourceNotFoundException;
import com.enigmacamp.pascal.sepotifay.repositories.GenreRepository;
import com.enigmacamp.pascal.sepotifay.services.GenreService;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class GenreServiceDBImpl implements GenreService {
  private final GenreRepository genreRepository;

  public GenreServiceDBImpl(GenreRepository genreRepository) {
    this.genreRepository = genreRepository;
  }

  @Override
  public void create(Genre entity) {
    genreRepository.save(entity);
  }

  @Override
  public void update(Genre entity) {
    getById(entity.getId());

    genreRepository.save(entity);
  }

  @Override
  public void delete(String id) {
    try {
      genreRepository.deleteById(id);
    } catch (EmptyResultDataAccessException erda) {
      throw new ResourceNotFoundException(id);
    }
  }

  @Override
  public Genre getById(String id) {
    return genreRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
  }

  @Override
  public Page<Genre> getAll(Pageable pager) {
    return genreRepository.findAll(pager);
  }

  @Override
  public Page<Genre> search(String keyword, Pageable pageable) {
    return genreRepository.findAllByNameContains(keyword, pageable);
  }
}
