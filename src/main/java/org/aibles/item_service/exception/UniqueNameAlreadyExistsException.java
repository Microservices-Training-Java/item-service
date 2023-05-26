package org.aibles.item_service.exception;

public class UniqueNameAlreadyExistsException extends BaseExceptionRequest {

  public UniqueNameAlreadyExistsException(String idField, String fieldUniqueName) {
    setStatus(409);
    setCode("org.aibles.item_service.exception.ExitstByUniqueNameException");
    addParams("name", fieldUniqueName);
  }

}
