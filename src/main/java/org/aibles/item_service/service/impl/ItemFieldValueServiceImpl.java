package org.aibles.item_service.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.aibles.item_service.dto.response.ItemFieldValueResponse;
import org.aibles.item_service.entity.ItemFieldValue;
import org.aibles.item_service.entity.ItemTypeField;
import org.aibles.item_service.exception.DuplicateKeyException;
import org.aibles.item_service.repository.ItemFieldValueRepository;
import org.aibles.item_service.service.ItemFieldValueService;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
public class ItemFieldValueServiceImpl implements ItemFieldValueService {

  private final ItemFieldValueRepository repository;

  public ItemFieldValueServiceImpl(ItemFieldValueRepository repository) {
    this.repository = repository;
  }

  @Override
  @Transactional
  public ItemFieldValueResponse create(String itemId, String fieldId, String value) {
    log.info("(create)itemId: {}, fieldId: {}, value: {}", itemId, fieldId, value);
    try {
      return ItemFieldValueResponse.from(repository.save(ItemFieldValue.of(itemId, fieldId, value)));
    } catch (DuplicateKeyException er) {
      log.error("(create)exception duplicate: {}", er.getClass().getName());
      throw new DuplicateKeyException();
    }
  }
}
