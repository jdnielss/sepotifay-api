package com.enigmacamp.pascal.sepotifay.controllers;

import com.enigmacamp.pascal.sepotifay.constants.ArtistMessageConstant;
import com.enigmacamp.pascal.sepotifay.constants.CommonConstant;
import com.enigmacamp.pascal.sepotifay.entities.Artist;
import com.enigmacamp.pascal.sepotifay.services.ArtistService;
import com.enigmacamp.pascal.sepotifay.utils.responses.ApiPagedResponse;
import com.enigmacamp.pascal.sepotifay.utils.responses.ApiResponse;
import com.enigmacamp.pascal.sepotifay.utils.responses.ResponseUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/artists")
public class ArtistController {
  private final ArtistService artistService;
  private final ResponseUtil<Artist> responseUtil;

  public ArtistController(ResponseUtil<Artist> responseUtil, ArtistService artistService) {
    this.responseUtil = responseUtil;
    this.artistService = artistService;
  }

  @GetMapping
  public ResponseEntity<ApiPagedResponse<Artist>> getAll(@RequestParam(name = "page", defaultValue = "1") Integer page) {
    PageRequest pageRequest = PageRequest.of(page, CommonConstant.ROWS_PER_PAGE, Sort.by(Sort.Direction.ASC, "name"));
    Page<Artist> artists = artistService.getAll(pageRequest);

    return responseUtil.build(artists);
  }

  @GetMapping("/{id}")
  public ResponseEntity<ApiResponse<Artist>> getById(@PathVariable String id) {
    Artist artist = artistService.getById(id);

    return responseUtil.build(artist);
  }

  @PostMapping
  public ResponseEntity<ApiResponse<Artist>> create(@RequestPart MultipartFile file, @RequestPart String body) throws RuntimeException {
    ObjectMapper mapper = new ObjectMapper();
    try {
      Artist artist = mapper.readValue(body, Artist.class);
      artistService.create(artist, file);

      String message = String.format(ArtistMessageConstant.ARTIST_CREATED, artist.getName());

      return responseUtil.build(artist, HttpStatus.CREATED, message);
    } catch (JsonProcessingException exception) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid data: " + body);
    }
  }

  @PutMapping
  public ResponseEntity<ApiResponse<Artist>> update(@RequestBody Artist artist) {
    artistService.update(artist);
    String message = String.format(ArtistMessageConstant.ARTIST_UPDATED, artist.getName());

    return responseUtil.build(artist, HttpStatus.OK, message);
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(code = HttpStatus.NO_CONTENT)
  public void delete(@PathVariable String id) {
    artistService.delete(id);
  }
}
