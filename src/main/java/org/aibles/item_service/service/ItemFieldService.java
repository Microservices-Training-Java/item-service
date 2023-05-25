package org.aibles.item_service.service;

import org.aibles.item_service.dto.request.ItemFieldRequest;
import org.aibles.item_service.dto.response.ItemFieldResponse;

public interface ItemFieldService {

  ItemFieldResponse create(ItemFieldRequest request);
}
