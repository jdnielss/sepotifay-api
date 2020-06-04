package com.enigmacamp.pascal.sepotifay.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class ResourceNotFoundException extends RuntimeException {
  public ResourceNotFoundException(String id) {
    super(String.format("Resource ID %s was not found.", id));
  }
}
