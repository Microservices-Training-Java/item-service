package org.aibles.item_service.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.aibles.item_service.dto.request.ItemFieldRequest;
import org.aibles.item_service.dto.response.ItemFieldResponse;
import org.aibles.item_service.entity.ItemField;
import org.aibles.item_service.exception.NameAlreadyExistsException;
import org.aibles.item_service.repository.ItemFieldRepository;
import org.aibles.item_service.service.ItemFieldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ItemFieldServiceImpl implements ItemFieldService {

  @Autowired
  private final ItemFieldRepository repository;

  public ItemFieldServiceImpl(ItemFieldRepository repository) {
    this.repository = repository;
  }

  @Override
  public ItemFieldResponse create(ItemFieldRequest request) {
    log.info("(create)request :{}", request.getName(), request.getUniqueName());
    String name = request.getName();
    String uniqueName = request.getUniqueName();
    if (repository.existsByName(name)) {
      log.error("(create)name: {} -> EXIST EXCEPTION", name);
      throw new NameAlreadyExistsException(name, ItemField.class.getSimpleName());
    }
    if (repository.existsByName(uniqueName)) {
      log.error("(create)uniqueName: {} -> EXIST EXCEPTION", uniqueName);
      throw new NameAlreadyExistsException(uniqueName,
          ItemField.class.getSimpleName());
    }
    return ItemFieldResponse.from(
        repository.save(ItemField.of(name, uniqueName)));
  }
}
