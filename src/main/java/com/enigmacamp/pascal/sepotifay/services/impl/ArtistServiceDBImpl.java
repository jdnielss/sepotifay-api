package com.enigmacamp.pascal.sepotifay.services.impl;

import com.enigmacamp.pascal.sepotifay.entities.Artist;
import com.enigmacamp.pascal.sepotifay.repositories.ArtistRepository;
import com.enigmacamp.pascal.sepotifay.services.ArtistService;
import com.enigmacamp.pascal.sepotifay.utils.file.FileUtil;
import org.apache.commons.io.FilenameUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;

@Service
public class ArtistServiceDBImpl implements ArtistService {
  private final ArtistRepository artistRepository;
  private final FileUtil fileUtil;

  public ArtistServiceDBImpl(ArtistRepository artistRepository, FileUtil fileUtil) {
    this.artistRepository = artistRepository;
    this.fileUtil = fileUtil;
  }

  @Override
  public void create(Artist entity) {
    artistRepository.save(entity);
  }

  @Override
  public void create(Artist entity, MultipartFile file) {
    try {
      artistRepository.save(entity);

      String destination = String.format("artists/%s.%s",
          entity.getId().replaceAll("-", ""),
          FilenameUtils.getExtension(file.getOriginalFilename())
      );

      String path = fileUtil.store(file, destination);
      System.out.println(path);

      entity.setPhoto(path);
      artistRepository.save(entity);

    } catch (IOException ioe) {
      ioe.printStackTrace();
      throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Something happened during file upload process.");
    }
  }

  @Override
  public void update(Artist entity) {
    artistRepository.save(entity);
  }

  @Override
  public void delete(String id) {
    artistRepository.deleteById(id);
  }

  @Override
  public Artist getById(String id) {
    return artistRepository.findById(id).orElse(null);
  }

  @Override
  public Page<Artist> getAll(Pageable pager) {
    return artistRepository.findAll(pager);
  }
}
