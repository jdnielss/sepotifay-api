package com.enigmacamp.pascal.sepotifay.controllers;

import com.enigmacamp.pascal.sepotifay.constants.CommonConstant;
import com.enigmacamp.pascal.sepotifay.constants.SongMessageConstant;
import com.enigmacamp.pascal.sepotifay.entities.Song;
import com.enigmacamp.pascal.sepotifay.services.SongService;
import com.enigmacamp.pascal.sepotifay.utils.responses.ApiPagedResponse;
import com.enigmacamp.pascal.sepotifay.utils.responses.ApiResponse;
import com.enigmacamp.pascal.sepotifay.utils.responses.ResponseUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/songs")
public class SongController {
  private final SongService songService;
  private final ResponseUtil<Song> responseUtil;

  public SongController(SongService songService, ResponseUtil<Song> responseUtil) {
    this.songService = songService;
    this.responseUtil = responseUtil;
  }

  @GetMapping
  public ResponseEntity<ApiPagedResponse<Song>> getAll(@RequestParam(name = "page", defaultValue = "1") Integer page) {
    PageRequest pageRequest = PageRequest.of(--page, CommonConstant.ROWS_PER_PAGE, Sort.by(Sort.Direction.ASC, "title"));
    Page<Song> songs = songService.getAll(pageRequest);

    return responseUtil.build(songs);
  }

  @GetMapping("/{id}")
  public ResponseEntity<ApiResponse<Song>> getById(@PathVariable String id) {
    Song song = songService.getById(id);

    return responseUtil.build(song);
  }

  @PostMapping
  public ResponseEntity<ApiResponse<Song>> create(@RequestBody Song song) {
    songService.create(song);
    String message = String.format(SongMessageConstant.SONG_CREATED, song.getTitle());

    return responseUtil.build(song, HttpStatus.CREATED, message);
  }

  @PutMapping
  public ResponseEntity<ApiResponse<Song>> update(@RequestBody Song song) {
    songService.update(song);
    String message = String.format(SongMessageConstant.SONG_UPDATED, song.getTitle());

    return responseUtil.build(song, HttpStatus.OK, message);
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(code = HttpStatus.NO_CONTENT)
  public void delete(@PathVariable String id) {
    songService.delete(id);
  }
}
