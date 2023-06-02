package org.aibles.item_service.service.impl;

import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.aibles.item_service.dto.request.ItemFieldCreateRequest;
import org.aibles.item_service.dto.response.ItemFieldResponse;
import org.aibles.item_service.entity.ItemField;
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
  @Transactional
  public ItemFieldResponse create(ItemFieldCreateRequest request) {
    log.info("(create)request :{}", request.getName(), request.getUniqueName());
    validateExistsFieldName(request.getName());
    validateExistsFieldUniqueName(request.getUniqueName());
    return ItemFieldResponse.from(
        repository.save(ItemField.of(request.getName(), request.getUniqueName())));
  }

  @Override
  @Transactional(readOnly = true)
  public List<FieldProjection> getAllByItemTypeId(String itemTypeId) {
    log.info("(getAllByItemTypeId)itemTypeId: {}", itemTypeId);
    return repository.findALLByItemTypeId(itemTypeId);
  }

  @Override
  @Transactional(readOnly = true)
  public String getNameById(String id) {
    log.info("(getNameById)id : {}", id);
    validateExistsFieldId(id);
    return repository.findNameById(id);
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
  public void validateExistsFieldName(String name) {
    log.info("(validateExistsFieldName)name :{}", name);
    if (repository.existsByName(name)) {
      throw new NotFoundException(name, ItemField.class.getSimpleName());
    }
  }

  @Override
  public void validateExistsFieldUniqueName(String uniqueName) {
    log.info("(validateExistsFieldName)uniqueName :{}", uniqueName);
    if (repository.existsByName(uniqueName)) {
      throw new NotFoundException(uniqueName, ItemField.class.getSimpleName());
    }
  }

}
