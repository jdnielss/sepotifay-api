package com.enigmacamp.pascal.sepotifay.utils.responses;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class ApiPagedResponse<T> {
  private ResponseStatus status;
  private List<T> data;
  private ResponsePage page;

  public ApiPagedResponse(List<T> data, ResponseStatus status, ResponsePage page) {
    this.status = status;
    this.data = data;
    this.page = page;
  }
}
