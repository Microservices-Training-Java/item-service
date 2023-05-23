package org.aibles.item_service.service;

import java.util.List;
import org.aibles.item_service.dto.response.ItemTypeFieldResponse;

public interface ItemTypeFieldService {

  ItemTypeFieldResponse create(String itemTypeId, String fieldId);

  String deleteById(String id);

  List<ItemTypeFieldResponse> getAll();

  ItemTypeFieldResponse getById(String id);

  List<ItemTypeFieldResponse> getByItemTypeId(String itemTypeId);
}
