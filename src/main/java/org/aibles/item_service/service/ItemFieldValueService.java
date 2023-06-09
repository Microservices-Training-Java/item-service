package org.aibles.item_service.service;

import java.util.List;
import org.aibles.item_service.dto.response.ItemFieldValueResponse;

public interface ItemFieldValueService {

  /**
   * create item field value
   * @param itemId - Id for item
   * @param fieldId - Id for field
   * @param value - value foe field
   * @return information of an item field value
   */
  ItemFieldValueResponse create (String itemId, String fieldId, String value,String imageId);

  /**
   * delete by item Id
   * @param itemId - Id for item
   */
  void deleteByItemId(String itemId);

  /**
   * show all field values by itemId
   * @param itemId - id of item
   * @return the information of field value according to itemId
   */
  List<ItemFieldValueResponse> getAllByItemId(String itemId);
}
