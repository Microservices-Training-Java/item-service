package org.aibles.item_service.facade;

import java.util.List;
import org.aibles.item_service.dto.response.ItemTypeDetailResponse;

public interface ItemTypeFacadeService {

  /**
   * create type field information
   * @param type - type you want to create
   * @param listField - the ids of the item-field you want to add to the type
   * @return type field information after adding
   */
  ItemTypeDetailResponse create(String type, List<String> listField);

  /**
   * delete type field information
   * @param id - id of type
   * @return message deleted successfully
   */
  String deleteById(String id);

  /**
   * get type field information by id
   * @param id - id of type
   * @return type field information
   */
  ItemTypeDetailResponse getById(String id);

  /**
   * update type field information
   * @param id - id of type
   * @param type - type you want to update
   * @param listField - the ids of the item-field you want to update to the type
   * @return type field information after updating
   */
  ItemTypeDetailResponse update(String id, String type, List<String> listField);
}
