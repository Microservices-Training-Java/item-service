package org.aibles.item_service.controller.advice;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.aibles.item_service.exception.BaseExceptionRequest;
import org.aibles.item_service.exception.response.ExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class HandleException {

  @ExceptionHandler(value = {BaseExceptionRequest.class})
  public ResponseEntity<ExceptionResponse> baseExceptionHandler(BaseExceptionRequest error) {
    log.error("(Exception) errorCode: {}", error.getCode());
    ExceptionResponse exceptionResponse = new ExceptionResponse();
    exceptionResponse.setStatus(error.getStatus());
    exceptionResponse.setError(error.getCode());
    exceptionResponse.setMessage(error.getParams());
    exceptionResponse.setTimestamp(Instant.now());
    return new ResponseEntity<>(exceptionResponse, HttpStatus.valueOf(error.getStatus()));
  }

  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException exception) {
    Map<String, String> errors = new HashMap<>();
    exception.getBindingResult().getAllErrors().forEach(error -> {
      String fieldName = ((FieldError) error).getField();
      String errorMessage = error.getDefaultMessage();
      errors.put(fieldName, errorMessage);
    });
    log.info("(inValid): {}", errors);
    return errors;
  }
}
