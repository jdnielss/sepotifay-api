package com.enigmacamp.pascal.sepotifay.advices;

import com.enigmacamp.pascal.sepotifay.exceptions.ResourceNotFoundException;
import com.enigmacamp.pascal.sepotifay.utils.responses.ApiErrorResponse;
import com.enigmacamp.pascal.sepotifay.utils.responses.ApiValidationErrorResponse;
import com.enigmacamp.pascal.sepotifay.utils.responses.ResponseStatus;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException.BadRequest;
import org.springframework.web.client.HttpClientErrorException.NotFound;
import org.springframework.web.client.HttpServerErrorException.InternalServerError;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

import static com.enigmacamp.pascal.sepotifay.constants.CommonConstant.VALIDATION_FAILED_MESSAGE;

@ControllerAdvice
public class ResponseEntityExceptionAdvice extends ResponseEntityExceptionHandler {
  private final ResponseStatus responseStatus;

  public ResponseEntityExceptionAdvice() {
    this.responseStatus = new ResponseStatus();
  }

  @ExceptionHandler(InternalServerError.class)
  public ResponseEntity<ApiErrorResponse> handleInternalServerError(Exception exception) {
    responseStatus.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
    responseStatus.setMessage(exception.getMessage());

    ApiErrorResponse response = new ApiErrorResponse(responseStatus);

    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
  }

  @ExceptionHandler(NotFound.class)
  public ResponseEntity<ApiErrorResponse> handleNotFoundError(Exception exception) {
    responseStatus.setCode(HttpStatus.NOT_FOUND.value());
    responseStatus.setMessage(exception.getMessage());

    ApiErrorResponse response = new ApiErrorResponse(responseStatus);

    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
  }

  @ExceptionHandler(ResourceNotFoundException.class)
  public ResponseEntity<ApiErrorResponse> handleResourceNotFoundError(ResourceNotFoundException exception) {
    responseStatus.setCode(HttpStatus.NOT_FOUND.value());
    responseStatus.setMessage(exception.getMessage());

    ApiErrorResponse response = new ApiErrorResponse(responseStatus);

    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
  }

  @ExceptionHandler(BadRequest.class)
  public ResponseEntity<ApiErrorResponse> handleBadRequestError(Exception exception) {
    responseStatus.setCode(HttpStatus.BAD_REQUEST.value());
    responseStatus.setMessage(exception.getMessage());

    ApiErrorResponse response = new ApiErrorResponse(responseStatus);

    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
  }

  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
    responseStatus.setCode(HttpStatus.BAD_REQUEST.value());
    responseStatus.setMessage(VALIDATION_FAILED_MESSAGE);

    List<String> validationErrors = ex.getBindingResult()
        .getFieldErrors()
        .stream()
        .map(DefaultMessageSourceResolvable::getDefaultMessage)
        .collect(Collectors.toList());

    ApiValidationErrorResponse errorResponse = new ApiValidationErrorResponse(responseStatus, validationErrors);

    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
  }
}
