package org.aibles.item_service.exception;

import static org.aibles.item_service.constant.ExceptionConstant.FIELD_ALREADY_EXCEPTION_CODE;

import org.trainingjava.core_exception.ConflictException;

public class FieldAlreadyExitException extends ConflictException {

  public FieldAlreadyExitException(String objectFieldId) {
    setCode(FIELD_ALREADY_EXCEPTION_CODE);
    addParams("objectFieldId", objectFieldId);

  }
}
