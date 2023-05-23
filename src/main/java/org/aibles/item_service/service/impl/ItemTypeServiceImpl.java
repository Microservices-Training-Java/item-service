package org.aibles.item_service.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.aibles.item_service.dto.response.ItemTypeResponse;
import org.aibles.item_service.entity.ItemType;
import org.aibles.item_service.exception.NotFoundException;
import org.aibles.item_service.repository.ItemTypeRepository;
import org.aibles.item_service.service.ItemTypeService;

@Slf4j
public class ItemTypeServiceImpl implements ItemTypeService {

  private final ItemTypeRepository repository;

  public ItemTypeServiceImpl(ItemTypeRepository repository) {
    this.repository = repository;
  }

  @Override
  public ItemTypeResponse create(String type) {
    log.info("(create)type: {}", type);
    if(repository.existsByType(type)) {
      log.error("(create)type : {} --> NOT FOUND EXCEPTION", type);
      throw new NotFoundException(type, ItemType.class.getSimpleName());
    }
    return ItemTypeResponse.from(repository.save(ItemType.of(type)));
  }

  @Override
  public String deleteById(String id) {
    log.info("(deleteById)id: {}", id);
    if(!repository.existsById(id)) {
      log.error("(deleteById)id : {} --> NOT FOUND EXCEPTION", id);
      throw new NotFoundException(id, ItemType.class.getSimpleName());
    }
    repository.deleteById(id);
    return "DELETE SUCCESS!!!";
  }
}
