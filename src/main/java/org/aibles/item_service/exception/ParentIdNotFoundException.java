package org.aibles.item_service.exception;

import org.trainingjava.core_exception.NotFoundException;

public class ParentIdNotFoundException extends NotFoundException {

  public ParentIdNotFoundException() {
    setCode("org.aibles.item_service.exception.ParentIdNotFoundException");
  }
}
