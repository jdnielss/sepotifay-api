package com.enigmacamp.pascal.sepotifay.controllers;

import com.enigmacamp.pascal.sepotifay.constants.AlbumMessagesConstant;
import com.enigmacamp.pascal.sepotifay.constants.CommonConstant;
import com.enigmacamp.pascal.sepotifay.entities.Album;
import com.enigmacamp.pascal.sepotifay.services.AlbumService;
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
@RequestMapping("/albums")
public class AlbumController {
  private final ResponseUtil<Album> responseUtil;
  private final AlbumService albumService;

  public AlbumController(ResponseUtil<Album> responseUtil, AlbumService albumService) {
    this.responseUtil = responseUtil;
    this.albumService = albumService;
  }

  @GetMapping
  public ResponseEntity<ApiPagedResponse<Album>> getAll(@RequestParam(name = "page", defaultValue = "1") Integer page) {
    PageRequest pageRequest = PageRequest.of(--page, CommonConstant.ROWS_PER_PAGE, Sort.by(Sort.Direction.ASC, "title"));
    Page<Album> albums = albumService.getAll(pageRequest);

    return responseUtil.build(albums);
  }

  @GetMapping("/search")
  public ResponseEntity<ApiPagedResponse<Album>> search(
      @RequestParam(name = "page", defaultValue = "1") Integer page,
      @RequestParam(name = "q", defaultValue = "") String keyword
  ) {
    PageRequest pageRequest = PageRequest.of(--page, CommonConstant.ROWS_PER_PAGE, Sort.by(Sort.Direction.ASC, "title"));
    Page<Album> albums = albumService.search(keyword, pageRequest);

    return responseUtil.build(albums);
  }

  @GetMapping("/{id}")
  public ResponseEntity<ApiResponse<Album>> getById(@PathVariable String id) {
    Album album = albumService.getById(id);

    return responseUtil.build(album);
  }

  @PostMapping
  public ResponseEntity<ApiResponse<Album>> create(@RequestBody Album album) {
    albumService.create(album);
    String message = String.format(AlbumMessagesConstant.ALBUM_CREATED, album.getTitle());

    return responseUtil.build(album, HttpStatus.CREATED, message);
  }

  @PutMapping("/{id}")
  public ResponseEntity<ApiResponse<Album>> update(@RequestBody Album album, @PathVariable String id) {
    albumService.update(album);
    String message = String.format(AlbumMessagesConstant.ALBUM_UPDATED, album.getTitle());

    return responseUtil.build(album, HttpStatus.OK, message);
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(code = HttpStatus.NO_CONTENT)
  public void delete(@PathVariable String id) {
    albumService.delete(id);
  }
}
