package org.aibles.item_service.exception;

import static org.aibles.item_service.constant.ExceptionConstant.UNIQUE_NAME_ALREADY_EXCEPTION_CODE;

import org.aibles.item_service.entity.Item;
import org.trainingjava.core_exception.ConflictException;

public class ItemFieldUniqueNameAlreadyExistsException extends ConflictException {

  public ItemFieldUniqueNameAlreadyExistsException(String id, String uniqueName) {
    setCode(UNIQUE_NAME_ALREADY_EXCEPTION_CODE);
    addParams("uniqueName", uniqueName);
  }

}
