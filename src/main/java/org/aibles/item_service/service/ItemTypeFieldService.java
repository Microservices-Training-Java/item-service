package org.aibles.item_service.service;

import java.util.List;
import org.aibles.item_service.dto.response.ItemTypeFieldResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ItemTypeFieldService {

  /**
   * type field initialization
   * @param itemTypeId - id of item-type
   * @param fieldId - id of item-field
   * @return - information of item-type-field
   */
  ItemTypeFieldResponse create(String itemTypeId, String fieldId);

  /**
   * remove type-field by typeId
   * @param itemTypeId - id of item-type
   */
  void deleteByTypeId(String itemTypeId);

  /**
   * get information of type-field based on typeId
   * @param itemTypeId - id of item-type
   * @return The information of the type-field of that typeId
   */
  List<ItemTypeFieldResponse> getAllByItemTypeId(String itemTypeId);

  /**
   * check for no duplicate fields on 1 type
   * @param itemTypeId - id of item-type
   * @param fieldId - id of item-field
   */
  void validateExistsItemTypeIdAndFieldId(String itemTypeId, String fieldId);

}
