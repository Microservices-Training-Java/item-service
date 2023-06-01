package org.aibles.item_service.exception;

import static org.aibles.item_service.constant.ExceptionConstant.NAME_ALREADY_EXCEPTION_CODE;

public class ItemFieldNameAlreadyExistsException extends BaseExceptionRequest{
  public ItemFieldNameAlreadyExistsException(String id, String name) {
    setStatus(409);
    setCode(NAME_ALREADY_EXCEPTION_CODE);
    addParams("name", name);
  }

}
