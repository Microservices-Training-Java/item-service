package org.aibles.item_service.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.aibles.item_service.dto.request.ItemFieldRequest;
import org.aibles.item_service.dto.response.ItemFieldResponse;
import org.aibles.item_service.entity.ItemField;
import org.aibles.item_service.exception.NameAlreadyExistsException;
import org.aibles.item_service.repository.ItemFieldRepository;
import org.aibles.item_service.service.ItemFieldService;

@Slf4j
public class ItemFieldServiceImpl implements ItemFieldService {

  private final ItemFieldRepository repository;

  public ItemFieldServiceImpl(ItemFieldRepository repository) {
    this.repository = repository;
  }

  @Override
  public ItemFieldResponse create(ItemFieldRequest request) {
    log.info("(create)request :{}", request.getName(), request.getUniqueName());
    if (repository.existsByName(request.getName())) {
      log.error("(create)name: {} -> EXIST EXCEPTION", request.getName());
      throw new NameAlreadyExistsException(request.getName(), ItemField.class.getSimpleName());
    }
    if (repository.existsByUniqueName(request.getUniqueName())) {
      log.error("(create)uniqueName: {} -> EXIST EXCEPTION", request.getUniqueName());
      throw new NameAlreadyExistsException(request.getUniqueName(),
          ItemField.class.getSimpleName());
    }
    return ItemFieldResponse.from(
        repository.save(ItemField.of(request.getName(), request.getUniqueName())));
  }
}