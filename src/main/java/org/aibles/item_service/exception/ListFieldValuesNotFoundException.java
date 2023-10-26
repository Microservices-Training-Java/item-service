package org.aibles.item_service.exception;

import static org.aibles.item_service.constant.ExceptionConstant.LIST_FIELD_VALUES_NOT_FOUND_EXCEPTION_CODE;

import java.util.List;
import org.aibles.item_service.dto.ItemFieldValueDto;
import org.trainingjava.core_exception.NotFoundException;

public class ListFieldValuesNotFoundException extends NotFoundException {

  public ListFieldValuesNotFoundException(List<ItemFieldValueDto> object) {
    setCode(LIST_FIELD_VALUES_NOT_FOUND_EXCEPTION_CODE);
    addParams("object", object.toString());
  }
}
