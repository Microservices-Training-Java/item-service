package org.aibles.item_service.service;

import java.util.List;
import org.aibles.item_service.dto.request.ItemTypeCreateRequest;
import org.aibles.item_service.dto.response.ItemTypeDetailResponse;
import org.aibles.item_service.dto.response.ItemTypeResponse;

public interface ItemTypeService {

  ItemTypeDetailResponse create(String type, List<String> listField);

  String deleteById(String id);

  List<ItemTypeResponse> getAll();


  ItemTypeResponse update(String id, String type);
  ItemTypeDetailResponse getById(String id);

  void validateExist(String id);
}
