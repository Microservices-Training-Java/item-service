package org.aibles.item_service.service;

import org.aibles.item_service.dto.request.ItemFieldCreateRequest;
import org.aibles.item_service.dto.request.ItemFieldUpdateRequest;
import org.aibles.item_service.dto.response.ItemFieldResponse;
import org.aibles.item_service.dto.response.ItemFieldValueResponse;
import org.aibles.item_service.entity.ItemField;

public interface ItemFieldService {

  /**
   * check if there is id of that field
   *
   * @param id - id of field
   */
  void validateExistsFieldId(String id);

  /**
   * get the name of the field
   *
   * @param id - id of field
   * @return name that you search based on id
   */
  String getNameById(String id);

  /*
   *create a field from request
   * save the field into database
   * @return the field by response
   */
  ItemFieldResponse create(ItemFieldCreateRequest request);

  ItemFieldResponse update(String id, ItemFieldUpdateRequest request);
}
