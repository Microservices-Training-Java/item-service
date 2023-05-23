package org.aibles.item_service.exception;

public class NotFoundException extends BaseExceptionRequest{

  public NotFoundException(String objectId, String objectType) {
    setStatus(404);
    setCode("org.aibles.item_service.exception.NotFoundException");
    addParams("id", objectId);
    addParams("type", objectType);
  }
}
