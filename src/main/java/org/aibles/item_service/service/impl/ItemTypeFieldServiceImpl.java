package org.aibles.item_service.service.impl;

import java.util.List;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.aibles.item_service.dto.response.ItemTypeFieldResponse;
import org.aibles.item_service.entity.ItemTypeField;
import org.aibles.item_service.exception.DuplicateKeyException;
import org.aibles.item_service.exception.FieldAlreadyExitException;
import org.aibles.item_service.exception.NotFoundException;
import org.aibles.item_service.repository.ItemTypeFieldRepository;
import org.aibles.item_service.service.ItemFieldService;
import org.aibles.item_service.service.ItemTypeFieldService;

import org.springframework.transaction.annotation.Transactional;

@Slf4j
public class ItemTypeFieldServiceImpl implements ItemTypeFieldService {

  private final ItemTypeFieldRepository repository;
  private final ItemFieldService itemFieldService;

  public ItemTypeFieldServiceImpl(ItemTypeFieldRepository repository,
       ItemFieldService itemFieldService) {
    this.repository = repository;
    this.itemFieldService = itemFieldService;
  }

  @Override
  @Transactional
  public ItemTypeFieldResponse create(String itemTypeId, String fieldId) {
    log.info("(create)itemTypeId: {}, fieldId: {}", itemTypeId, fieldId);
    itemFieldService.validateExist(fieldId);
    if (repository.existsByItemTypeIdAndFieldId(itemTypeId, fieldId)) {
      log.error("(create)itemTypeId: {}, fieldId: {}", itemTypeId, fieldId);
      throw new FieldAlreadyExitException(itemTypeId, fieldId);
    }
    try {
      return ItemTypeFieldResponse.from(repository.save(ItemTypeField.of(itemTypeId,fieldId)));
    } catch (DuplicateKeyException er) {
      log.error("(create)exception duplicate: {}", er.getClass().getName());
      throw new DuplicateKeyException();
    }
  }

  @Override
  @Transactional
  public String deleteByTypeId(String itemTypeId) {
    log.info("(deleteByTypeId)itemTypeId: {}", itemTypeId);
    if(!repository.existsByItemTypeId(itemTypeId)) {
      log.error("(deleteByTypeId)itemTypeId : {} --> NOT FOUND EXCEPTION", itemTypeId);
      throw new NotFoundException(itemTypeId, ItemTypeField.class.getSimpleName());
    }
    repository.deleteAllByItemTypeId(itemTypeId);
    return "DELETE TYPE FIELD SUCCESS!!!";
  }

  @Override
  @Transactional(readOnly = true)
  public List<ItemTypeFieldResponse> getAllByItemTypeId(String itemTypeId) {
    log.info("(getByItemTypeId)itemTypeId: {}", itemTypeId);
    return repository.findAllByItemTypeId(itemTypeId).stream()
        .map(ItemTypeFieldResponse::from)
        .collect(Collectors.toList());
  }

  @Override
  public List<String> getFieldIdByItemTypeId(String itemTypeId) {
    log.info("(getFieldIdByItemTypeId)itemTypeId: {}", itemTypeId);
    if(!repository.existsByItemTypeId(itemTypeId)) {
      log.error("(deleteByTypeId)itemTypeId : {} --> NOT FOUND EXCEPTION", itemTypeId);
      throw new NotFoundException(itemTypeId, ItemTypeField.class.getSimpleName());
    }
    return repository.findFieldIdByItemTypeId(itemTypeId);
  }

  @Override
  @Transactional
  public void update(List<String> listFeald, String typeId) {
    log.info("(update)typeId: {}, listFeald: {}", typeId, listFeald);
    for (String value : listFeald) {
      create(typeId, value);
    }
  }
}
