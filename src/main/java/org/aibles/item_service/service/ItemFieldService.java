package org.aibles.item_service.service;

import java.util.List;
import org.aibles.item_service.dto.request.ItemFieldCreateRequest;
import org.aibles.item_service.dto.response.ItemFieldPaginationResponse;
import org.aibles.item_service.dto.response.ItemFieldResponse;
import org.aibles.item_service.repository.FieldProjection;

public interface ItemFieldService {

  /**
   * check if there is id of that field
   *
   * @param id - id of field
   */
  void validateExistsFieldId(String id);

  /*
   * check if field name exists
   */
  void validateExistsFieldName(String name);

  /*
   * check if field uniqueName exists
   */
  void validateExistsFieldUniqueName(String uniqueName);


  /**
   * get the name of the field
   *
   * @param id - id of field
   * @return name that you search based on id
   */
  String getNameById(String id);

  /*
   *create a field from request
   * save the field into database
   * @return the field by response

   */
  ItemFieldResponse create(ItemFieldCreateRequest request);

  /**
   * function get fields by itemTypeId
   *
   * @param itemTypeId - id of item-type
   * @return show fields
   */
  List<FieldProjection> getAllByItemTypeId(String itemTypeId);

  /**
   * function get all item fields
   *
   * @return item field list
   */
  List<ItemFieldResponse> getAll();

  /**
   * paging itemfield
   *
   * @param pageNumber, pageSize, totalPages
   * @return item field list, number, size of page
   */
  ItemFieldPaginationResponse getItemFieldPagination(int pageNumber, int pageSize);
}
