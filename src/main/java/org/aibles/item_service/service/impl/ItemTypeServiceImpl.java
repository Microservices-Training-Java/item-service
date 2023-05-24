package org.aibles.item_service.service.impl;

import java.util.List;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.aibles.item_service.dto.response.ItemTypeDetailResponse;
import org.aibles.item_service.dto.response.ItemTypeResponse;
import org.aibles.item_service.entity.ItemType;
import org.aibles.item_service.exception.TypeAlreadyExistsException;
import org.aibles.item_service.exception.BadRequestException;
import org.aibles.item_service.exception.NotFoundException;
import org.aibles.item_service.repository.ItemTypeRepository;
import org.aibles.item_service.service.ItemFieldService;
import org.aibles.item_service.service.ItemTypeFieldService;
import org.aibles.item_service.service.ItemTypeService;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
public class ItemTypeServiceImpl implements ItemTypeService {

  private final ItemTypeRepository repository;
  private final ItemFieldService itemFieldService;
  private final ItemTypeFieldService itemTypeFieldService;

  public ItemTypeServiceImpl(ItemTypeRepository repository, ItemFieldService itemFieldService,
      ItemTypeFieldService itemTypeFieldService) {
    this.repository = repository;
    this.itemFieldService = itemFieldService;
    this.itemTypeFieldService = itemTypeFieldService;
  }

  @Override
  @Transactional
  public ItemTypeDetailResponse create(String type, List<String> listField) {
    log.info("(create)type: {}", type);
    if(repository.existsByType(type)) {
      log.error("(create)type : {} --> EXIST EXCEPTION", type);
      throw new TypeAlreadyExistsException(type, ItemType.class.getSimpleName());
    }
    var itemType = repository.save(ItemType.of(type));
    for (String value : listField) {
      var itemTypeField = itemTypeFieldService.create(itemType.getId(), value);
    }
    return ItemTypeDetailResponse.from(itemType, listField);
  }

  @Override
  @Transactional
  public String deleteById(String id) {
    log.info("(deleteById)id: {}", id);
    if(!repository.existsById(id)) {
      log.error("(deleteById)id : {} --> NOT FOUND EXCEPTION", id);
      throw new NotFoundException(id, ItemType.class.getSimpleName());
    }
    itemTypeFieldService.deleteByTypeId(id);
    repository.deleteById(id);
    return "DELETE TYPE SUCCESS!!!";
  }

  @Override
  @Transactional(readOnly = true)
  public List<ItemTypeResponse> getAll() {
    log.info("(getAll)item type");
    return repository.findAll().stream()
        .map(ItemTypeResponse::from)
        .collect(Collectors.toList());
  }

  @Override
  @Transactional
  public ItemTypeResponse update(String id, String type) {
    log.info("(update)id: {}, type: {}", id, type);
    var itemType = repository
        .findById(id)
        .orElseThrow(() -> {
          log.error("(update)id : {} --> NOT FOUND EXCEPTION", id);
          throw new NotFoundException(id, ItemType.class.getSimpleName());
        });
    if(repository.existsByType(type)) {
      log.error("(update)type : {} --> EXIST EXCEPTION", type);
      throw new TypeAlreadyExistsException(type, ItemType.class.getSimpleName());
    }
    itemType.setId(id);
    itemType.setType(type);
    return ItemTypeResponse.from(repository.save(itemType));
  }

  @Override
  @Transactional(readOnly = true)
  public ItemTypeDetailResponse getById(String id) {
    log.info("(getById)id: {}", id);
    var itemType = repository
        .findById(id)
        .orElseGet(() -> {
          log.error("(getById)id : {} --> NOT FOUND EXCEPTION", id);
          throw new NotFoundException(id, ItemType.class.getSimpleName());
        });
    return null;
  }

  @Override
  @Transactional(readOnly = true)
  public void validateExist(String id) {
    log.info("(validateExist)id : {}", id);
    if (!repository.existsById(id)) {
      throw new NotFoundException(id, ItemType.class.getSimpleName());
    }
  }
}
