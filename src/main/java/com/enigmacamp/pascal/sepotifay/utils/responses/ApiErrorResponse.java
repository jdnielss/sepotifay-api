package com.enigmacamp.pascal.sepotifay.utils.responses;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ApiErrorResponse {
  private ResponseStatus status;

  public ApiErrorResponse() {
  }

  public ApiErrorResponse(ResponseStatus status) {
    this.status = status;
  }
}
