package org.aibles.item_service.service;

import org.aibles.item_service.dto.request.ItemTypeCreateRequest;
import org.aibles.item_service.dto.response.ItemTypeResponse;

public interface ItemTypeService {

  ItemTypeResponse create(String type);

  String deleteById(String id);

  ItemTypeResponse update(String id, String type);
}
