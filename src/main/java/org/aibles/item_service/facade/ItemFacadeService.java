package org.aibles.item_service.facade;

import java.util.List;
import org.aibles.item_service.dto.ItemFieldValueDto;
import org.aibles.item_service.dto.request.ItemIdRequest;
import org.aibles.item_service.dto.response.ItemDetailResponse;
import org.aibles.item_service.dto.response.ListItemDetailResponse;
import org.aibles.item_service.repository.ItemProjection;

public interface ItemFacadeService {


  /**
   * create item and item-field-value
   * @param itemTypeId - id of itemType
   * @param fieldValue - is a list of fieldId and value
   * @return item information and map information between fieldId,value
   */
  ItemDetailResponse create(String itemTypeId, List<ItemFieldValueDto> fieldValue);

  /**
   * delete by item Id
   * @param id - id of item
   * @param itemTypeId - id of item type
   */
  void deleteById(String id, String itemTypeId);

  /**
   * when user delete item type id it will delete all item
   * @param itemTypeId - itemTypeId of item type
   */
  void deleteAllByItemTypeId(String itemTypeId);

  /**
   * get by id
   * @param id - id of item
   * @param itemTypeId - id of item type
   * @return item detail information
   */
  ItemDetailResponse getById(String id, String itemTypeId);

  /**
   * function does not use query to execute in case the query is not completed
   * get list item
   * @param listItem - list itemId
   * @return - information of the items you want to display
   */
  ListItemDetailResponse getListItem(List<ItemIdRequest> listItem);

  List<ItemProjection> getListItems(List<ItemIdRequest> listItem);


  /**
   * update by id
   * @param id - id of item
   * @param itemTypeId - id of itemType
   * @param fieldValue - is a list of fieldId and value
   * @return  item information and map information between fieldId,value
   */
  ItemDetailResponse update(String id, String itemTypeId, List<ItemFieldValueDto> fieldValue);
}
