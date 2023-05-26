package org.aibles.item_service.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.aibles.item_service.dto.request.ItemFieldRequest;
import org.aibles.item_service.dto.response.ItemFieldResponse;
import org.aibles.item_service.entity.ItemField;
import org.aibles.item_service.exception.NameAlreadyExistsException;
import org.aibles.item_service.exception.NotFoundException;
import org.aibles.item_service.exception.UniqueNameAlreadyExistsException;
import org.aibles.item_service.repository.ItemFieldRepository;
import org.aibles.item_service.service.ItemFieldService;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
public class ItemFieldServiceImpl implements ItemFieldService {

  private final ItemFieldRepository repository;

  public ItemFieldServiceImpl(ItemFieldRepository repository) {
    this.repository = repository;
  }

  @Override
  @Transactional
  public ItemFieldResponse create(ItemFieldRequest request) {
    log.info("(create)request :{}", request.getName(), request.getUniqueName());
    if (repository.existsByName(request.getName())) {
      log.error("(create)name: {} -> EXIST EXCEPTION", request.getName());
      throw new NameAlreadyExistsException(request.getName(), ItemField.class.getSimpleName());
    }
    if (repository.existsByName(request.getUniqueName())) {
      log.error("(create)uniqueName: {} -> EXIST EXCEPTION", request.getUniqueName());
      throw new UniqueNameAlreadyExistsException(request.getUniqueName(),
          ItemField.class.getSimpleName());
    }
    return ItemFieldResponse.from(
        repository.save(ItemField.of(request.getName(), request.getUniqueName())));
  }

  @Override
  public String deleteById(String id) {
    log.info("(deleteById)id: {}", id);
    if (!repository.existsById(id)) {
      log.error("(deleteById)id : {}-> NOT FOUND EXCEPTION", id);
      throw new NotFoundException(id, ItemField.class.getSimpleName());
    }
    repository.deleteById(id);
    return "DELETE SUCCESS!!!";
  }

  @Override
  public ItemFieldResponse update(String id, ItemFieldRequest request) {
    log.info("(update)id : {}, request : {}", id, request);
    ItemField itemField = repository
        .findById(id)
        .orElseThrow(() -> {
              log.error("(update)id : {} --> NOT FOUND EXCEPTION", id);
              throw new NotFoundException(id, ItemField.class.getSimpleName());
            }
        );
    if (repository.existsByName(request.getName())) {
      log.error("(update)name : {} --> EXIST EXCEPTION", request.getName());
      throw new NameAlreadyExistsException(request.getName(), ItemField.class.getSimpleName());
    }
    if (repository.existsByUniqueName(request.getUniqueName())) {
      log.error("(update)uniqueName : {} --> EXIST EXCEPTION", request.getUniqueName());
      throw new UniqueNameAlreadyExistsException(request.getUniqueName(),
          ItemField.class.getSimpleName());
    }
    itemField.setName(request.getName());
    itemField.setUniqueName(request.getUniqueName());
    return ItemFieldResponse.from(repository.save(itemField));
  }
}
