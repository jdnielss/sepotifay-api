package com.enigmacamp.pascal.sepotifay.services.impl;

import com.enigmacamp.pascal.sepotifay.entities.Album;
import com.enigmacamp.pascal.sepotifay.repositories.AlbumRepository;
import com.enigmacamp.pascal.sepotifay.services.AlbumService;
import com.enigmacamp.pascal.sepotifay.specifications.AlbumJpaSpecification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlbumServiceDBImpl implements AlbumService {
  private final AlbumRepository albumRepository;

  public AlbumServiceDBImpl(AlbumRepository albumRepository) {
    this.albumRepository = albumRepository;
  }

  @Override
  public void create(Album entity) {
    albumRepository.save(entity);
  }

  @Override
  public void update(Album entity) {
    albumRepository.save(entity);
  }

  @Override
  public void delete(String id) {
    albumRepository.deleteById(id);
  }

  @Override
  public Album getById(String id) {
    return albumRepository.findById(id).orElse(null);
  }

  @Override
  public Page<Album> getAll(Pageable pager) {
    return albumRepository.findAll(pager);
  }

  @Override
  public Page<Album> search(String keyword, Pageable pageable) {
    Album form = new Album(keyword, keyword);

    if (keyword.chars().allMatch(Character::isDigit))
      form.setReleaseYear(Integer.parseInt(keyword));

    return albumRepository.findAll(AlbumJpaSpecification.findByCriteria(form), pageable);
  }
}
