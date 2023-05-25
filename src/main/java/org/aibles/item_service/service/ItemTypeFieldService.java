package org.aibles.item_service.service;

import java.util.List;
import org.aibles.item_service.dto.response.ItemTypeFieldResponse;

public interface ItemTypeFieldService {

  ItemTypeFieldResponse create(String itemTypeId, String fieldId);

  String deleteByTypeId(String itemTypeId);

  List<ItemTypeFieldResponse> getAllByItemTypeId(String itemTypeId);

  List<String> getFieldIdByItemTypeId(String itemTypeId);

  void update(List<String> listFeald, String typeId);
}
