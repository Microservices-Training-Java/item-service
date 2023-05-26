package org.aibles.item_service.exception;

import static org.aibles.item_service.constant.ExceptionConstant.MAP_NOTFOUND_EXCEPTION_CODE;

import java.util.Map;

public class MapNotFoundException extends BaseExceptionRequest{

  public MapNotFoundException(Map<String, String> object) {
    setStatus(404);
    setCode(MAP_NOTFOUND_EXCEPTION_CODE);
    addParams("object", object);
  }
}
