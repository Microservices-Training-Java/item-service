package org.aibles.item_service.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.aibles.item_service.dto.response.ItemTypeDetailResponse;
import org.aibles.item_service.dto.response.ItemTypeFieldResponse;
import org.aibles.item_service.dto.response.ItemTypeResponse;
import org.aibles.item_service.entity.ItemType;
import org.aibles.item_service.entity.ItemTypeField;
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
    itemTypeFieldService.update(listField, itemType.getId());
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
  @Transactional(readOnly = true)
  public ItemTypeDetailResponse getById(String id) {
    log.info("(getById)id: {}", id);
    var itemType = repository
        .findById(id)
        .orElseGet(() -> {
          log.error("(getById)id : {} --> NOT FOUND EXCEPTION", id);
          throw new NotFoundException(id, ItemType.class.getSimpleName());
        });
    var itemTypeField = itemTypeFieldService.getAllByItemTypeId(id);

    List<String> list = new ArrayList<>();
    for (ItemTypeFieldResponse value : itemTypeField) {
      list.add(itemFieldService.getNameById(value.getFieldId()));
    }
    return ItemTypeDetailResponse.from(itemType, list);
  }

  @Override
  @Transactional
  public ItemTypeDetailResponse update(String id, String type, List<String> listField) {
    log.info("(update)id: {}, type: {}", id, type);
    var itemType = repository
        .findById(id)
        .orElseThrow(() -> {
          log.error("(update)id : {} --> NOT FOUND EXCEPTION", id);
          throw new NotFoundException(id, ItemType.class.getSimpleName());
        });
    if(!itemType.getType().equals(type) && repository.existsByType(type)) {
      log.error("(update)type : {} --> EXIST EXCEPTION", type);
      throw new TypeAlreadyExistsException(type, ItemType.class.getSimpleName());
    }
    itemType.setId(id);
    itemType.setType(type);
    repository.save(itemType);
    itemTypeFieldService.deleteByTypeId(id);
    itemTypeFieldService.update(listField, id);
    return ItemTypeDetailResponse.from(itemType, listField);
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
