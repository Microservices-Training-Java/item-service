package org.aibles.item_service.exception;

import static org.aibles.item_service.constant.ExceptionConstant.DUPLICATE_EXCEPTION_CODE;

public class DuplicateKeyException extends BaseExceptionRequest {

  public DuplicateKeyException() {
    setStatus(409);
    setCode(DUPLICATE_EXCEPTION_CODE);
  }
}
