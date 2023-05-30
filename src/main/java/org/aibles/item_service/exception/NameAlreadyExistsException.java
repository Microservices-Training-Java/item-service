package org.aibles.item_service.exception;

public class NameAlreadyExistsException extends BaseExceptionRequest {

  public NameAlreadyExistsException(String fieldId, String fieldName) {
    setStatus(409);
    setCode("org.aibles.item_service.exception.ExitstByNameException");
    addParams("name", fieldName);

  }

}