package org.aibles.item_service.service.impl;

import java.util.List;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.aibles.item_service.dto.response.ItemTypeResponse;
import org.aibles.item_service.entity.ItemType;
import org.aibles.item_service.exception.TypeAlreadyExistsException;
import org.aibles.item_service.exception.NotFoundException;
import org.aibles.item_service.repository.ItemTypeRepository;
import org.aibles.item_service.service.ItemTypeService;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
public class ItemTypeServiceImpl implements ItemTypeService {

  private final ItemTypeRepository repository;

  public ItemTypeServiceImpl(ItemTypeRepository repository) {
    this.repository = repository;
  }

  @Override
  @Transactional
  public ItemTypeResponse create(String type) {
    log.info("(create)type: {}", type);
    if(repository.existsByType(type)) {
      log.error("(create)type : {} --> EXIST EXCEPTION", type);
      throw new TypeAlreadyExistsException(type, ItemType.class.getSimpleName());
    }
    return ItemTypeResponse.from(repository.save(ItemType.of(type)));
  }

  @Override
  @Transactional
  public String deleteById(String id) {
    log.info("(deleteById)id: {}", id);
    if(!repository.existsById(id)) {
      log.error("(deleteById)id : {} --> NOT FOUND EXCEPTION", id);
      throw new NotFoundException(id, ItemType.class.getSimpleName());
    }
    repository.deleteById(id);
    return "DELETE SUCCESS!!!";
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
  public void validateExist(String id) {
    log.info("(validateExist)id : {}", id);
    if (!repository.existsById(id)) {
      throw new NotFoundException(id, ItemType.class.getSimpleName());
    }
  }
}
