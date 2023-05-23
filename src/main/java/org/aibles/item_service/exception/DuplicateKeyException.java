package org.aibles.item_service.exception;

public class DuplicateKeyException extends BaseExceptionRequest {

  public DuplicateKeyException() {
    setStatus(409);
    setCode("org.ptit.okrs.core.exception.DuplicateKeyException");
  }
}
