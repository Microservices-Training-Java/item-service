package org.aibles.item_service.exception;

import static org.aibles.item_service.constant.ExceptionConstant.FIELD_ALREADY_EXCEPTION_CODE;

public class FieldAlreadyExitException extends BaseExceptionRequest {

  public FieldAlreadyExitException(String objectFieldId) {
    setStatus(409);
    setCode(FIELD_ALREADY_EXCEPTION_CODE);
    addParams("objectFieldId", objectFieldId);

  }
}
