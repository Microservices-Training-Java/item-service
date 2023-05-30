package org.aibles.item_service.service;

import java.util.List;
import org.aibles.item_service.dto.response.ItemTypeResponse;
import org.aibles.item_service.entity.ItemType;

public interface ItemTypeService {

  /**
   * create item-type
   * @param type - type you want to enter
   * @return information of item type
   */
  ItemTypeResponse create(String type);

  /**
   * delete item-type
   * @param id - id of type
   */
  void deleteById(String id);

  /**
   * show all item-types
   * @return show all item-types
   */
  List<ItemTypeResponse> getAll();

  /**
   * get item-type by id
   * @param id - id of type
   * @return information of item type
   */
  ItemType getById(String id);

  /**
   * update item-type
   * @param id - id of type
   * @param type - type you want to update
   * @return information of item type
   */
  ItemTypeResponse update(String id, String type);

  /**
   * check if there is id of that type
   * @param id - id of type
   */
  void validateExistsItemTypeId(String id);

  /**
   * check if there is id of that type
   * @param type - type of item-type
   */
  void validateExistsType(String type);
}
