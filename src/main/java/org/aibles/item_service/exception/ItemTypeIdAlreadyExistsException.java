package org.aibles.item_service.exception;

import static org.aibles.item_service.constant.ExceptionConstant.ITEMTYPEID_ALREADY_EXCEPTION_CODE;

public class ItemTypeIdAlreadyExistsException extends BaseExceptionRequest{
  public ItemTypeIdAlreadyExistsException(String objectId, String objectType) {
    setStatus(409);
    setCode(ITEMTYPEID_ALREADY_EXCEPTION_CODE);
    addParams("type", objectType);
  }
}
