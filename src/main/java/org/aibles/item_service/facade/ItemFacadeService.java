package org.aibles.item_service.facade;

import java.util.List;
import java.util.Map;
import org.aibles.item_service.dto.response.ItemDetailResponse;

public interface ItemFacadeService {

  /**
   * create item and item-field-value
   * @param itemTypeId - id of itemType
   * @param fieldValue - is a map between fieldId and value
   * @return item information and map information between fieldId and value
   */
  ItemDetailResponse create(String itemTypeId, Map<String, String> fieldValue);

  /**
   * delete by item Id
   * @param id - id of item
   */
  void deleteById(String id);

  /**
   * when user delete item type id it will delete all item
   * @param itemTypeId - itemTypeId of item type
   */
  void deleteAllByItemTypeId(String itemTypeId);

  /**
   * get by id
   * @param id - id of item
   * @return item detail information
   */
  ItemDetailResponse getById(String id);

  ItemDetailResponse update(String id, String itemTypeId, Map<String, String> fieldValue);
}
