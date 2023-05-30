package org.aibles.item_service.exception;

public class UniqueNameAlreadyExistsException extends BaseExceptionRequest {

  public UniqueNameAlreadyExistsException(String fieldId, String fieldName) {
    setStatus(409);
    setCode("org.aibles.item_service.exception.ExitstByNameException");
    addParams("name", fieldName);

  }

}