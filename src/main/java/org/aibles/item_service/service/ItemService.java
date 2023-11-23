package org.aibles.item_service.service;

import org.aibles.item_service.dto.request.ItemCalculateRequest;
import org.aibles.item_service.dto.response.DetailResponse;
import org.aibles.item_service.dto.response.ItemResponse;
import org.aibles.item_service.dto.response.ItemTotalOrderPriceResponse;

import java.util.List;
import java.util.Set;

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

  /**
   * get list item
   * @param itemIds - itemIds want to display information
   * @return - information of the items you want to display
   */
  DetailResponse getItem(Set<String> itemIds);

  /**
   * upđate item by id
   * @param id - id of item
   * @param itemTypeId - id of item type
   * @return information of an item
   */
  ItemResponse updateById(String id, String itemTypeId);

  ItemTotalOrderPriceResponse calculateOrder(ItemCalculateRequest request);

  /**
   * get Price Item
   * @param itemId
   * @return
   */
  String getPriceItem(String itemId);

  /**
   * The function retrieves the values in the item that the user needs
   * @param itemId - The function retrieves the values in the item that the user needs
   * @param name - field name to get
   * @return The value of the name to get
   */
  String getValueItemByItemIdAndName(String itemId, String name);

  /**
   * get item id from table itemFieldValue by item name
   * @param name
   * @return item id
   */
  Set<String> getItemIdByName(String name);
}
