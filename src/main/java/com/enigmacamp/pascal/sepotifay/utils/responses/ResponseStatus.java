package com.enigmacamp.pascal.sepotifay.utils.responses;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter @Setter
public class ResponseStatus {
  private int code = HttpStatus.OK.value();
  private String message = "OK";
}
