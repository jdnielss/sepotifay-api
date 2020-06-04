package com.enigmacamp.pascal.sepotifay.utils.responses;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class ApiValidationErrorResponse extends ApiErrorResponse {
  private List<String> errors;

  public ApiValidationErrorResponse(ResponseStatus status, List<String> errors) {
    super(status);
    this.errors = errors;
  }
}
