package org.aibles.item_service.exception;

import static org.aibles.item_service.constant.ExceptionConstant.TYPE_ALREADY_EXCEPTION_CODE;

import org.trainingjava.core_exception.ConflictException;

public class TypeAlreadyExistsException extends ConflictException {

  public TypeAlreadyExistsException(String objectId, String objectType) {
    setCode(TYPE_ALREADY_EXCEPTION_CODE);
    addParams("type", objectType);
  }
}
