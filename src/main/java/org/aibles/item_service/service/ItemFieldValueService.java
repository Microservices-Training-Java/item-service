package org.aibles.item_service.service;

import org.aibles.item_service.dto.response.ItemFieldValueResponse;

public interface ItemFieldValueService {

  /**
   * create item field value
   * @param itemId - Id for item
   * @param fieldId - Id for field
   * @param value - value foe field
   * @return information of an item field value
   */
  ItemFieldValueResponse create (String itemId, String fieldId, String value);

  /**
   * delete by item Id
   * @param itemId - Id for item
   */
  void deleteByItemId(String itemId);
}
