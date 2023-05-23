package org.aibles.item_service.service.impl;

import java.util.List;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.aibles.item_service.dto.response.ItemTypeFieldResponse;
import org.aibles.item_service.entity.ItemField;
import org.aibles.item_service.entity.ItemTypeField;
import org.aibles.item_service.exception.DuplicateKeyException;
import org.aibles.item_service.exception.NotFoundException;
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
    try {
      return ItemTypeFieldResponse.from(repository.save(ItemTypeField.of(itemTypeId,fieldId)));
    } catch (DuplicateKeyException er) {
      log.error("(create)exception duplicate: {}", er.getClass().getName());
      throw new DuplicateKeyException();
    }
  }

  @Override
  @Transactional
  public String deleteById(String id) {
    log.info("(deleteById)id: {}", id);
    if(!repository.existsById(id)) {
      log.error("(deleteById)id : {} --> NOT FOUND EXCEPTION", id);
      throw new NotFoundException(id, ItemTypeField.class.getSimpleName());
    }
    repository.deleteById(id);
    return "DELETE SUCCESS!!!";
  }

  @Override
  @Transactional(readOnly = true)
  public List<ItemTypeFieldResponse> getAll() {
    log.info("(getAll)item type field");
    return repository.findAll().stream()
        .map(ItemTypeFieldResponse::from)
        .collect(Collectors.toList());
  }

  @Override
  @Transactional(readOnly = true)
  public ItemTypeFieldResponse getById(String id) {
    log.info("(getById)id: {}", id);
    var itemTypeField = repository
        .findById(id)
        .orElseThrow(() -> {
          log.error("(deleteById)id : {} --> NOT FOUND EXCEPTION", id);
          throw new NotFoundException(id, ItemTypeField.class.getSimpleName());
    });
    return ItemTypeFieldResponse.from(itemTypeField);
  }

  @Override
  public List<ItemTypeFieldResponse> getByItemTypeId(String itemTypeId) {
    log.info("(getByItemTypeId)itemTypeId: {}", itemTypeId);
    itemTypeService.validateExist(itemTypeId);
    return null;
  }
}
