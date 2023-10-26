package org.aibles.item_service.exception;

public class ConflictException extends BaseExceptionRequest{

  public ConflictException() {
    setStatus(409);
    setCode("org.aibles.item_service.exception.ConflictException");
  }
}
