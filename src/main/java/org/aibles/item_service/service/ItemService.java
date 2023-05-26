package org.aibles.item_service.service;

import org.aibles.item_service.dto.response.ItemResponse;

public interface ItemService{

  /**
   * Create item
   * @param itemTypeId - id of itemType
   * @return information of an item
   */
  ItemResponse create(String itemTypeId);

}
