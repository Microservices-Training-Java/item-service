package org.aibles.item_service.exception;

public class BadRequestException extends BaseExceptionRequest{

  public BadRequestException(String objectId, String objectType) {
    setStatus(400);
    setCode("org.aibles.item_service.exception.BadRequestException");
    addParams("id", objectId);
    addParams("type", objectType);
  }
}
