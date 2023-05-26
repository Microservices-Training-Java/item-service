package org.aibles.item_service.service.impl;

import java.util.List;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.aibles.item_service.dto.response.ItemFieldValueResponse;
import org.aibles.item_service.dto.response.ItemTypeFieldResponse;
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

  @Override
  @Transactional(readOnly = true)
  public List<ItemFieldValueResponse> getAllByItemId(String itemId) {
    log.info("(getAllByItemId)itemId: {}", itemId);
    return repository.findAllByItemId(itemId)
        .stream()
        .map(ItemFieldValueResponse::from).
        collect(Collectors.toList());
  }
}
