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
  ItemDetailResponse create(String itemTypeId, Map<String, String> fieldValue,String image_id);
}
