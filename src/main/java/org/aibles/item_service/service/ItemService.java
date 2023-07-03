package org.aibles.item_service.service;

import java.util.List;
import org.aibles.item_service.dto.request.ItemIdRequest;
import org.aibles.item_service.dto.response.ItemResponse;
import org.aibles.item_service.dto.response.ListItemDetailResponse;
import org.aibles.item_service.repository.ItemProjection;

public interface ItemService{

  /**
   * Create item
   * @param itemTypeId - id of item type
   * @return information of an item
   */
  ItemResponse create(String itemTypeId);

  /**
   * delete by id for item
   * @param id- id of item
   */
  void deleteById(String id);

  /**
   * when user delete item type id it will delete all item
   * @param itemTypeId - itemTypeId of item type
   */
  void deleteAllByItemTypeId(String itemTypeId);

  /**
   * get the item's information by itemTypeId
   * @param itemTypeId - itemTypeId of item type
   * @return items based on itemTypeId
   */
  List<ItemResponse> getAllByItemTypeId(String itemTypeId);

  /**
   * get by id
   * @param id - id of item
   * @return item's information
   */
  ItemResponse getById(String id);

  List<ItemProjection> getItem(List<ItemIdRequest> listItem);

  /**
   * upđate item by id
   * @param id - id of item
   * @param itemTypeId - id of item type
   * @return information of an item
   */
  ItemResponse updateById(String id, String itemTypeId);

}
