package com.enigmacamp.pascal.sepotifay.controllers;

import com.enigmacamp.pascal.sepotifay.services.ArtistService;
import com.enigmacamp.pascal.sepotifay.utils.file.FileUtil;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@RestController
public class FileController {
  FileUtil fileUtil;
  ArtistService artistService;

  public FileController(FileUtil fileUtil, ArtistService artistService) {
    this.fileUtil = fileUtil;
    this.artistService = artistService;
  }

  @GetMapping("/artists/photos/{path}")
  public ResponseEntity<Resource> getArtistsPhotos(@PathVariable String path, HttpServletRequest request) {
    String artistPath = String.format("artists/%s", path);
    Resource resource = fileUtil.read(artistPath);

    String contentType = null;

    try {
      contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
    } catch (IOException ex) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "File not found.");
    }

    return ResponseEntity.ok()
        .contentType(MediaType.parseMediaType(contentType))
        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
        .body(resource);
  }
}
