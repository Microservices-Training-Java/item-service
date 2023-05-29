package org.aibles.item_service.service.impl;

import java.util.List;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.aibles.item_service.dto.response.ItemResponse;
import org.aibles.item_service.dto.response.ItemTypeFieldResponse;
import org.aibles.item_service.entity.Item;
import org.aibles.item_service.exception.DuplicateKeyException;
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
  public void deleteById(String id) {
    log.info("(deleteById)id: {}", id);
    if (!repository.existsById(id)) {
      log.error("(deleteById)id : {} --> NOT FOUND EXCEPTION", id);
      throw new NotFoundException(id, Item.class.getSimpleName());
    }
    repository.deleteById(id);
  }

  @Override
  @Transactional
  public void deleteAllByItemTypeId(String itemTypeId) {
    log.info("(deleteAllByItemTypeId)itemTypeId: {}", itemTypeId);
    if (!repository.existsByItemTypeId(itemTypeId)) {
      log.error("(deleteAllByItemTypeId)itemTypeId : {} --> NOT FOUND EXCEPTION", itemTypeId);
      throw new NotFoundException(itemTypeId, Item.class.getSimpleName());
    }
    repository.deleteAllByItemTypeId(itemTypeId);
  }

  @Override
  @Transactional(readOnly = true)
  public List<ItemResponse> getAllByItemTypeId(String itemTypeId) {
    log.info("(getByItemTypeId)itemTypeId: {}", itemTypeId);
    if (!repository.existsByItemTypeId(itemTypeId)) {
      log.error("(getByItemTypeId)itemTypeId : {} --> NOT FOUND EXCEPTION", itemTypeId);
      throw new NotFoundException(itemTypeId, Item.class.getSimpleName());
    }
    return repository.findAllByItemTypeId(itemTypeId)
        .stream()
        .map(ItemResponse::from).
        collect(Collectors.toList());
  }

  @Override
  @Transactional(readOnly = true)
  public ItemResponse getById(String id) {
    log.info("(getById)id: {}", id);
    var item = repository
        .findById(id)
        .orElseThrow(() -> {
          log.error("(getById)id : {} --> NOT FOUND EXCEPTION", id);
          throw new NotFoundException(id, Item.class.getSimpleName());
        });
    return ItemResponse.from(item);
  }

  @Override
  @Transactional
  public ItemResponse updateById(String id, String itemTypeId) {
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
