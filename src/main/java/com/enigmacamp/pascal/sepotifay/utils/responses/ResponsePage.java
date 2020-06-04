package com.enigmacamp.pascal.sepotifay.utils.responses;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ResponsePage {
  private Integer page;
  private Integer rows;
  private Integer totalPages;
  private Long totalRows;
}
