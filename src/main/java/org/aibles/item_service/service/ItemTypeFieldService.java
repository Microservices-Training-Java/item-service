package org.aibles.item_service.service;

import org.aibles.item_service.dto.response.ItemTypeFieldResponse;

public interface ItemTypeFieldService {

  ItemTypeFieldResponse create(String itemTypeId, String fieldId);
}
