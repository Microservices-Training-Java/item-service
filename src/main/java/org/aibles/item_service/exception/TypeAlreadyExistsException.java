package org.aibles.item_service.exception;

public class TypeAlreadyExistsException extends BaseExceptionRequest{

  public TypeAlreadyExistsException(String objectId, String objectType) {
    setStatus(409);
    setCode("org.aibles.item_service.exception.ExitstByTypeException");
    addParams("type", objectType);
  }
}
