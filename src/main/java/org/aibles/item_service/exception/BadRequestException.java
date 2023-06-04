package org.aibles.item_service.exception;

public class BadRequestException extends BaseExceptionRequest{

  public BadRequestException() {
    setStatus(400);
    setCode("org.aibles.item_service.exception.BadRequestException");

  }
}
