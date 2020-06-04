package com.enigmacamp.pascal.sepotifay.controllers;

import com.enigmacamp.pascal.sepotifay.constants.CommonConstant;
import com.enigmacamp.pascal.sepotifay.constants.GenreMessagesConstant;
import com.enigmacamp.pascal.sepotifay.entities.Genre;
import com.enigmacamp.pascal.sepotifay.services.GenreService;
import com.enigmacamp.pascal.sepotifay.utils.responses.ApiPagedResponse;
import com.enigmacamp.pascal.sepotifay.utils.responses.ApiResponse;
import com.enigmacamp.pascal.sepotifay.utils.responses.ResponseUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/genres")
public class GenreController {
  private final ResponseUtil<Genre> responseUtil;
  private final GenreService genreService;

  public GenreController(ResponseUtil<Genre> responseUtil, GenreService genreService) {
    this.responseUtil = responseUtil;
    this.genreService = genreService;
  }

  @GetMapping
  public ResponseEntity<ApiPagedResponse<Genre>> getAll(@RequestParam(name = "page", defaultValue = "1") Integer page) {
    Pageable pageable = PageRequest.of(--page, CommonConstant.ROWS_PER_PAGE, Sort.by(Sort.Direction.ASC, "name"));
    Page<Genre> pagedData = genreService.getAll(pageable);

    return responseUtil.build(pagedData);
  }

  @GetMapping("/search")
  public ResponseEntity<ApiPagedResponse<Genre>> search(@RequestParam(name = "q", defaultValue = "") String keyword, @RequestParam(name = "page", defaultValue = "1") Integer page) {
    Pageable pageable = PageRequest.of(--page, CommonConstant.ROWS_PER_PAGE, Sort.by(Sort.Direction.ASC, "name"));
    Page<Genre> pagedData = genreService.search(keyword, pageable);

    return responseUtil.build(pagedData);
  }

  @GetMapping("/{id}")
  public ResponseEntity<ApiResponse<Genre>> get(@PathVariable String id) {
    Genre genre = genreService.getById(id);

    return responseUtil.build(genre);
  }

  @PostMapping
  public ResponseEntity<ApiResponse<Genre>> create(@Valid @RequestBody Genre genre) {
    genreService.create(genre);
    String message = String.format(GenreMessagesConstant.GENRE_CREATED, genre.getName());

    return responseUtil.build(genre, HttpStatus.CREATED, message);
  }

  @PutMapping
  public ResponseEntity<ApiResponse<Genre>> update(@RequestBody Genre genre) {
    genreService.update(genre);
    String message = String.format(GenreMessagesConstant.GENRE_UPDATED, genre.getId());

    return responseUtil.build(genre, HttpStatus.OK, message);
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(code = HttpStatus.NO_CONTENT)
  public void delete(@PathVariable String id) {
    genreService.delete(id);
  }
}
