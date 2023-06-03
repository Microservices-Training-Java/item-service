package org.aibles.item_service.exception;

import static org.aibles.item_service.constant.ExceptionConstant.UNIQUE_NAME_ALREADY_EXCEPTION_CODE;

import org.aibles.item_service.entity.Item;

public class ItemFieldUniqueNameAlreadyExistsException extends BaseExceptionRequest {

  public ItemFieldUniqueNameAlreadyExistsException(String id, String uniqueName) {
    setStatus(409);
    setCode(UNIQUE_NAME_ALREADY_EXCEPTION_CODE);
    addParams("uniqueName", uniqueName);
  }

}
