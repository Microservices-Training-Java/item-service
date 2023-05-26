package org.aibles.item_service.service;

import org.aibles.item_service.dto.response.ItemResponse;

public interface ItemService{

  /**
   * Create item
   * @param itemTypeId - id of itemType
   * @return information of an item
   */
  ItemResponse create(String itemTypeId);

  /**
   * get by id
   * @param id - id of item
   * @return item's information
   */
  ItemResponse getById(String id);

}
