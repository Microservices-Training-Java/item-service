package org.aibles.item_service.exception;

import org.trainingjava.core_exception.ConflictException;

public class CategoryNameAlreadyExitException extends ConflictException {

  public CategoryNameAlreadyExitException() {
    setCode("org.aibles.item_service.exception.CategoryNameAlreadyExitException");

  }
}
