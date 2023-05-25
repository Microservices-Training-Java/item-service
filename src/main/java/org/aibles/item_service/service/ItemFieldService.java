package org.aibles.item_service.service;

import org.aibles.item_service.entity.ItemField;

public interface ItemFieldService {

  /**
   * check if there is id of that field
   * @param id - id of field
   */
  void existsById (String id);

  /**
   * get the name of the field
   * @param id - id of field
   * @return name that you search based on id
   */
  String getNameById(String id);
}
