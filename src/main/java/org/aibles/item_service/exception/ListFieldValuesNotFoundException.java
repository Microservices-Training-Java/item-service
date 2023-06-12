package org.aibles.item_service.exception;

import static org.aibles.item_service.constant.ExceptionConstant.LIST_FIELD_VALUES_NOT_FOUND_EXCEPTION_CODE;

import java.util.List;
import org.aibles.item_service.dto.ItemFieldValueDto;

public class ListFieldValuesNotFoundException extends BaseExceptionRequest{

  public ListFieldValuesNotFoundException(List<ItemFieldValueDto> object) {
    setStatus(404);
    setCode(LIST_FIELD_VALUES_NOT_FOUND_EXCEPTION_CODE);
    addParams("object", object);
  }
}
