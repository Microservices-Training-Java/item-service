package org.aibles.item_service.service;

import java.util.List;
import org.aibles.item_service.dto.response.ItemTypeFieldResponse;

public interface ItemTypeFieldService {

  ItemTypeFieldResponse create(String itemTypeId, String fieldId);

  String deleteByTypeId(String itemTypeId);

  List<ItemTypeFieldResponse> getAll();

  List<ItemTypeFieldResponse> getAllByItemTypeId(String itemTypeId);

  ItemTypeFieldResponse update(String id, String itemTypeId, String fieldId);
}
