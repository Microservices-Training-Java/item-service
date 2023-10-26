package org.aibles.item_service.service.impl;

import java.util.List;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.aibles.item_service.dto.response.ItemTypeFieldResponse;
import org.aibles.item_service.entity.ItemTypeField;
import org.aibles.item_service.exception.FieldAlreadyExitException;
import org.aibles.item_service.repository.ItemTypeFieldRepository;
import org.aibles.item_service.service.ItemTypeFieldService;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.transaction.annotation.Transactional;
import org.trainingjava.core_exception.NotFoundException;

@Slf4j
public class ItemTypeFieldServiceImpl implements ItemTypeFieldService {

  private final ItemTypeFieldRepository repository;

  public ItemTypeFieldServiceImpl(ItemTypeFieldRepository repository) {
    this.repository = repository;
  }

  @Override
  @Transactional
  public ItemTypeFieldResponse create(String itemTypeId, String fieldId) {
    log.info("(create)itemTypeId: {}, fieldId: {}", itemTypeId, fieldId);
    try {
      return ItemTypeFieldResponse.from(repository.save(ItemTypeField.of(itemTypeId, fieldId)));
    } catch (DuplicateKeyException er) {
      log.error("(create)exception duplicate: {}", er.getClass().getName());
      throw new DuplicateKeyException(er.getClass().getName());
    }
  }

  @Override
  @Transactional
  public void deleteByTypeId(String itemTypeId) {
    log.info("(deleteByTypeId)itemTypeId: {}", itemTypeId);
    if (!repository.existsByItemTypeId(itemTypeId)) {
      log.error("(deleteByTypeId)itemTypeId : {} --> NOT FOUND EXCEPTION", itemTypeId);
      throw new NotFoundException(itemTypeId, ItemTypeField.class.getSimpleName());
    }
    repository.deleteAllByItemTypeId(itemTypeId);
  }

  @Override
  @Transactional(readOnly = true)
  public List<ItemTypeFieldResponse> getAllByItemTypeId(String itemTypeId) {
    log.info("(getByItemTypeId)itemTypeId: {}", itemTypeId);
    return repository.findAllByItemTypeId(itemTypeId)
        .stream()
        .map(ItemTypeFieldResponse::from).
        collect(Collectors.toList());
  }

  @Override
  @Transactional(readOnly = true)
  public void validateExistsItemTypeIdAndFieldId(String itemTypeId, String fieldId) {
    if (repository.existsByItemTypeIdAndFieldId(itemTypeId, fieldId)) {
      log.error("(validateExistsItemTypeIdAndFieldId)itemTypeId: {}, fieldId: {}", itemTypeId, fieldId);
      throw new FieldAlreadyExitException(fieldId);
    }
  }

}
