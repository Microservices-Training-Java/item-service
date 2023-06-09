package org.aibles.item_service.service.impl;

import java.util.List;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.aibles.item_service.dto.response.ItemTypePaginationResponse;
import org.aibles.item_service.dto.response.ItemTypeResponse;
import org.aibles.item_service.entity.ItemType;
import org.aibles.item_service.exception.DuplicateKeyException;
import org.aibles.item_service.exception.NotFoundException;
import org.aibles.item_service.exception.TypeAlreadyExistsException;
import org.aibles.item_service.repository.ItemTypeRepository;
import org.aibles.item_service.service.ItemTypeService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
    validateExistsType(type);
    try {
      return ItemTypeResponse.from(repository.save(ItemType.of(type)));
    } catch (DuplicateKeyException er) {
      log.error("(create)exception duplicate: {}", er.getClass().getName());
      throw new DuplicateKeyException();
    }
  }

  @Override
  @Transactional
  public void deleteById(String id) {
    log.info("(deleteById)id: {}", id);
    if (!repository.existsById(id)) {
      log.error("(deleteById)id : {} --> NOT FOUND EXCEPTION", id);
      throw new NotFoundException(id, ItemType.class.getSimpleName());
    }
    repository.deleteById(id);
  }
  @Override
  public ItemTypePaginationResponse getAll(int currentPage, int pageSize) {
    log.info("(getAll)currentPage :{}, pageSize :{}", currentPage, pageSize);
    Pageable pageable = PageRequest.of(currentPage, pageSize);
    Page<ItemType> page = repository.findAll(pageable);
    ItemTypePaginationResponse response = new ItemTypePaginationResponse(page.getContent(),
        page.getNumber(), page.getSize(), page.getTotalPages());
    return response;
  }

//  @Override
//  @Transactional(readOnly = true)
//  public List<ItemTypeResponse> getAll() {
//    log.info("(getAll)item type");
//    return repository.findAll().stream()
//        .map(ItemTypeResponse::from)
//        .collect(Collectors.toList());
//  }

  @Override
  @Transactional(readOnly = true)
  public ItemTypeResponse getById(String id) {
    log.info("(getById)id: {}", id);
    var itemType = repository
        .findById(id)
        .orElseThrow(() -> {
          log.error("(getById)id : {} --> NOT FOUND EXCEPTION", id);
          throw new NotFoundException(id, ItemType.class.getSimpleName());
        });
    return ItemTypeResponse.from(itemType);
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
    if (!itemType.getType().equals(type) && repository.existsByType(type)) {
      log.error("(update)type : {} --> EXIST EXCEPTION", type);
      throw new TypeAlreadyExistsException(type, ItemType.class.getSimpleName());
    }
    itemType.setId(id);
    itemType.setType(type);
    return ItemTypeResponse.from(repository.save(itemType));
  }

  @Override
  @Transactional(readOnly = true)
  public void validateExistsItemTypeId(String id) {
    log.info("(validateExistsItemTypeId)id : {}", id);
    if (!repository.existsById(id)) {
      throw new NotFoundException(id, ItemType.class.getSimpleName());
    }
  }

  @Override
  @Transactional(readOnly = true)
  public void validateExistsType(String type) {
    if (repository.existsByType(type)) {
      log.error("(validateExistsType)type : {} --> EXIST EXCEPTION", type);
      throw new TypeAlreadyExistsException(type, ItemType.class.getSimpleName());
    }
  }
}
