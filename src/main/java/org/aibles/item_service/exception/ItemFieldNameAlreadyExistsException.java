package org.aibles.item_service.exception;

import static org.aibles.item_service.constant.ExceptionConstant.NAME_ALREADY_EXCEPTION_CODE;

import org.trainingjava.core_exception.ConflictException;

public class ItemFieldNameAlreadyExistsException extends ConflictException {
  public ItemFieldNameAlreadyExistsException(String id, String name) {
    setCode(NAME_ALREADY_EXCEPTION_CODE);
    addParams("name", name);
  }

}
