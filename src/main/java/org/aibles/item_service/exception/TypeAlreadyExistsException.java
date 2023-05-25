package org.aibles.item_service.exception;

import static org.aibles.item_service.constant.ExceptionConstant.TYPE_ALREADY_EXCEPTION_CODE;

public class TypeAlreadyExistsException extends BaseExceptionRequest{

  public TypeAlreadyExistsException(String objectId, String objectType) {
    setStatus(409);
    setCode(TYPE_ALREADY_EXCEPTION_CODE);
    addParams("type", objectType);
  }
}
