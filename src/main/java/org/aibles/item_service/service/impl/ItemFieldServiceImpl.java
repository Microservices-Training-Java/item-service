package org.aibles.item_service.service.impl;

import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.aibles.item_service.dto.request.ItemFieldCreateRequest;
import org.aibles.item_service.dto.response.ItemFieldResponse;
import org.aibles.item_service.dto.response.ItemFieldValueResponse;
import org.aibles.item_service.entity.ItemField;
import org.aibles.item_service.entity.ItemType;
import org.aibles.item_service.exception.ItemFieldNameAlreadyExistsException;
import org.aibles.item_service.exception.ItemFieldUniqueNameAlreadyExistsException;
import org.aibles.item_service.exception.NotFoundException;
import org.aibles.item_service.repository.FieldProjection;
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
    validateExistsFieldId(id);
    return repository.findNameById(id);
  }

  @Override
  public ItemFieldResponse create(ItemFieldCreateRequest request) {
    log.info("(create)request :{}", request.getName(), request.getUniqueName());
    if (repository.existsByName(request.getName())) {
      log.error("(create)name :{} -> EXIST EXCEPTION", request.getName());
      throw new ItemFieldNameAlreadyExistsException(request.getName(),
          ItemField.class.getSimpleName());
    }
    if (repository.existsByUniqueName(request.getUniqueName())) {
      log.error("(create)uniqueName :{} -> EXIST EXCEPTION", request.getUniqueName());
      throw new ItemFieldUniqueNameAlreadyExistsException(request.getUniqueName(),
          ItemField.class.getSimpleName());
    }
    return ItemFieldResponse.from(
        repository.save(ItemField.of(request.getName(), request.getUniqueName())));
  }

  @Override
  @Transactional(readOnly = true)
  public void validateExistsFieldId(String id) {
    log.info("(validateExistsFieldId)id : {}", id);
    if (!repository.existsById(id)) {
      throw new NotFoundException(id, ItemField.class.getSimpleName());
    }
  }

  @Override
  @Transactional(readOnly = true)
  public List<FieldProjection> getAllByItemTypeId(String itemTypeId) {
    log.info("(getAllByItemTypeId)itemTypeId: {}", itemTypeId);
    return repository.findALLByItemTypeId(itemTypeId);
  }
}
