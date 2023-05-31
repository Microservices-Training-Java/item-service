package org.aibles.item_service.service;

import java.util.List;
import org.aibles.item_service.entity.ItemField;
import org.aibles.item_service.repository.FieldProjection;

public interface ItemFieldService {

  /**
   * check if there is id of that field
   * @param id - id of field
   */
  void validateExistsFieldId (String id);

  /**
   * get the name of the field
   * @param id - id of field
   * @return name that you search based on id
   */
  String getNameById(String id);

  /**
   * function get fields by itemTypeId
   * @param itemTypeId - id of item-type
   * @return show fields
   */
  List<FieldProjection> getAllByItemTypeId(String itemTypeId);
}
