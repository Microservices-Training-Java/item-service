package org.aibles.item_service.service;

import org.aibles.item_service.entity.ItemField;

public interface ItemFieldService {

  void validateExist(String id);

  String getNameById(String id);
}
