package org.aibles.item_service.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.aibles.item_service.entity.ItemField;
import org.aibles.item_service.entity.ItemType;
import org.aibles.item_service.exception.NotFoundException;
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
  @Transactional(readOnly = true)
  public String getNameById(String id) {
    log.info("(getNameById)id : {}", id);
    existsById (id);
    return repository.findNameById(id);
  }

  @Override
  @Transactional(readOnly = true)
  public void existsById(String id) {
    log.info("(validateExist)id : {}", id);
    if (!repository.existsById(id)) {
      throw new NotFoundException(id, ItemField.class.getSimpleName());
    }
  }
}
