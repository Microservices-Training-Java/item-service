package org.aibles.item_service.exception;

public class ExistsByTypeException extends BaseExceptionRequest{

  public ExistsByTypeException(String objectId, String objectType) {
    setStatus(409);
    setCode("org.aibles.item_service.exception.ExitstByTypeException");
    addParams("type", objectType);
  }
}
