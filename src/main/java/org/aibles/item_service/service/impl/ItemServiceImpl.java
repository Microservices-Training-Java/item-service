package org.aibles.item_service.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.aibles.item_service.dto.response.ItemResponse;
import org.aibles.item_service.entity.Item;
import org.aibles.item_service.entity.ItemField;
import org.aibles.item_service.entity.ItemType;
import org.aibles.item_service.exception.DuplicateKeyException;
import org.aibles.item_service.exception.ItemTypeIdAlreadyExistsException;
import org.aibles.item_service.exception.NotFoundException;
import org.aibles.item_service.repository.ItemRepository;
import org.aibles.item_service.service.ItemService;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
public class ItemServiceImpl implements ItemService {

  private final ItemRepository repository;

  public ItemServiceImpl(ItemRepository repository) {
    this.repository = repository;
  }

  @Override
  @Transactional
  public ItemResponse create(String itemTypeId) {
    log.info("(create)itemTypeId: {}", itemTypeId);
    try {
      return ItemResponse.from(repository.save(Item.of(itemTypeId)));
    } catch (DuplicateKeyException er) {
      log.error("(create)exception duplicate: {}", er.getClass().getName());
      throw new DuplicateKeyException();
    }
  }

  @Override
  @Transactional
  public ItemResponse update(String id, String itemTypeId) {
    log.info("(update)id: {}, itemTypeId: {}", id, itemTypeId);
    var item = repository
        .findById(id)
        .orElseThrow(() -> {
          log.error("(update)id : {} --> NOT FOUND EXCEPTION", id);
          throw new NotFoundException(id, Item.class.getSimpleName());
        });
    item.setId(id);
    item.setItemTypeId(itemTypeId);
    return ItemResponse.from(item);
  }

}
