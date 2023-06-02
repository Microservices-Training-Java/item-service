package org.aibles.item_service.facade;

import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.aibles.item_service.dto.response.ItemTypeDetailResponse;
import org.aibles.item_service.dto.response.ItemTypeFieldResponse;
import org.aibles.item_service.entity.ItemField;
import org.aibles.item_service.repository.FieldProjection;
import org.aibles.item_service.service.ItemFieldService;
import org.aibles.item_service.service.ItemTypeFieldService;
import org.aibles.item_service.service.ItemTypeService;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
public class ItemTypeFacadeServiceImpl implements ItemTypeFacadeService {

  private final ItemTypeService itemTypeService;
  private final ItemFieldService itemFieldService;
  private final ItemTypeFieldService itemTypeFieldService;
  private final ItemFacadeService itemFacadeService;


  public ItemTypeFacadeServiceImpl(
      ItemTypeService itemTypeService,
      ItemFieldService itemFieldService,
      ItemTypeFieldService itemTypeFieldService,
      ItemFacadeService itemFacadeService) {
    this.itemTypeService = itemTypeService;
    this.itemFieldService = itemFieldService;
    this.itemTypeFieldService = itemTypeFieldService;
    this.itemFacadeService = itemFacadeService;
  }

  @Override
  @Transactional
  public ItemTypeDetailResponse create(String type, List<String> listField) {
    log.info("(create)type: {}, listField: {}", type, listField);
    var itemType = itemTypeService.create(type);
    for (String value : listField) {
      itemFieldService.validateExistsFieldId(value);
      itemTypeFieldService.validateExistsItemTypeIdAndFieldId(itemType.getId(), value);
      itemTypeFieldService.create(itemType.getId(), value);
    }
    return ItemTypeDetailResponse.from(itemType, listField);
  }

  @Override
  @Transactional
  public void deleteById(String id) {
    log.info("(deleteById)id: {}", id);
    itemFacadeService.deleteAllByItemTypeId(id);
    itemTypeFieldService.deleteByTypeId(id);
    itemTypeService.deleteById(id);
  }

  @Override
  @Transactional(readOnly = true)
  public ItemTypeDetailResponse getById(String id) {
    log.info("(getById)id: {}", id);
    var itemType = itemTypeService.getById(id);
    var itemTypeField = itemTypeFieldService.getAllByItemTypeId(id);

    List<String> list = new ArrayList<>();
    for (ItemTypeFieldResponse value : itemTypeField) {
      list.add(itemFieldService.getNameById(value.getFieldId()));
    }
    return ItemTypeDetailResponse.from(itemType, list);
  }

  @Override
  @Transactional(readOnly = true)
  public List<FieldProjection> getFieldById(String id) {
    log.info("(getFieldById)id: {}", id);
    itemTypeService.validateExistsItemTypeId(id);
    return itemFieldService.getAllByItemTypeId(id);
  }

  /**
   * steps to update type b1: update type b2: remove type-field information by typeId b3: regenerate
   * type-field information after correcting fieldId
   */
  @Override
  @Transactional
  public ItemTypeDetailResponse update(String id, String type, List<String> listField) {
    log.info("(update)id: {}, type: {}", id, type);
    var itemType = itemTypeService.update(id, type);
    itemTypeFieldService.deleteByTypeId(id);
    for (String value : listField) {
      itemFieldService.validateExistsFieldId(value);
      itemTypeFieldService.validateExistsItemTypeIdAndFieldId(itemType.getId(), value);
      itemTypeFieldService.create(id, value);
    }
    return ItemTypeDetailResponse.from(itemType, listField);
  }
}
