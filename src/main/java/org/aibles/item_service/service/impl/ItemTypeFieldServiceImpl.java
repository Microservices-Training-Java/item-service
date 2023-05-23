package org.aibles.item_service.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.aibles.item_service.dto.response.ItemTypeFieldResponse;
import org.aibles.item_service.entity.ItemField;
import org.aibles.item_service.entity.ItemTypeField;
import org.aibles.item_service.repository.ItemTypeFieldRepository;
import org.aibles.item_service.service.ItemFieldService;
import org.aibles.item_service.service.ItemTypeFieldService;
import org.aibles.item_service.service.ItemTypeService;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
public class ItemTypeFieldServiceImpl implements ItemTypeFieldService {

  private final ItemTypeFieldRepository repository;
  private final ItemTypeService itemTypeService;
  private final ItemFieldService itemFieldService;

  public ItemTypeFieldServiceImpl(ItemTypeFieldRepository repository,
      ItemTypeService itemTypeService, ItemFieldService itemFieldService) {
    this.repository = repository;
    this.itemTypeService = itemTypeService;
    this.itemFieldService = itemFieldService;
  }

  @Override
  @Transactional
  public ItemTypeFieldResponse create(String itemTypeId, String fieldId) {
    log.info("(create)itemTypeId: {}, fieldId: {}", itemTypeId, fieldId);
    itemTypeService.validateExist(itemTypeId);
    itemFieldService.validateExist(fieldId);
    return ItemTypeFieldResponse.from(repository.save(ItemTypeField.of(itemTypeId,fieldId)));
  }
}
