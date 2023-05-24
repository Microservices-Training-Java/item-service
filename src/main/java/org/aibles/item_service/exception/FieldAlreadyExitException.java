package org.aibles.item_service.exception;

public class FieldAlreadyExitException extends BaseExceptionRequest {

  public FieldAlreadyExitException(String objectTypeId, String objectFieldId) {
    setStatus(409);
    setCode("org..aibles.item_service.exception.FieldAlreadyExitException");
    addParams("objectTypeId", objectTypeId);
    addParams("objectFieldId", objectFieldId);
  }
}
