package org.aibles.item_service.service;

import java.util.List;
import org.aibles.item_service.dto.request.ItemTypeCreateRequest;
import org.aibles.item_service.dto.response.ItemTypeResponse;

public interface ItemTypeService {

  ItemTypeResponse create(String type);

  String deleteById(String id);

  List<ItemTypeResponse> getAll();

}
