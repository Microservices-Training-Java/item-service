package org.aibles.item_service.facade;

import static org.aibles.item_service.constant.ItemConstant.MESSAGE_DELETE;

import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.aibles.item_service.dto.response.ItemTypeDetailResponse;
import org.aibles.item_service.dto.response.ItemTypeFieldResponse;
import org.aibles.item_service.service.ItemFieldService;
import org.aibles.item_service.service.ItemFieldValueService;
import org.aibles.item_service.service.ItemService;
import org.aibles.item_service.service.ItemTypeFieldService;
import org.aibles.item_service.service.ItemTypeService;

@Slf4j
public class ItemFacadeServiceImpl implements ItemFacadeService{

  private final ItemTypeService itemTypeService;
  private final ItemFieldService itemFieldService;
  private final ItemTypeFieldService itemTypeFieldService;
  private final ItemService itemService;
  private final ItemFieldValueService itemFieldValueService;

  public ItemFacadeServiceImpl(ItemTypeService itemTypeService, ItemFieldService itemFieldService,
      ItemTypeFieldService itemTypeFieldService, ItemService itemService,
      ItemFieldValueService itemFieldValueService) {
    this.itemTypeService = itemTypeService;
    this.itemFieldService = itemFieldService;
    this.itemTypeFieldService = itemTypeFieldService;
    this.itemService = itemService;
    this.itemFieldValueService = itemFieldValueService;
  }

  @Override
  public ItemTypeDetailResponse create(String type, List<String> listField) {
    log.info("(createTypeField)type: {}, listField: {}", type, listField);
    var itemType = itemTypeService.create(type);
    for (String value : listField) {
      itemTypeFieldService.existsByItemTypeIdAndFieldId(itemType.getId(), value);
      itemTypeFieldService.create(itemType.getId(), value);
    }
    return ItemTypeDetailResponse.from(itemType, listField);
  }

  @Override
  public String deleteById(String id) {
    log.info("(deleteById)id: {}", id);
    itemTypeFieldService.deleteByTypeId(id);
    itemTypeService.deleteById(id);
    return MESSAGE_DELETE;
  }

  @Override
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

  /**
   * steps to update type
   * b1: update type
   * b2: remove type-field information by typeId
   * b3: regenerate type-field information after correcting fieldId
   */
  @Override
  public ItemTypeDetailResponse update(String id, String type, List<String> listField) {
    log.info("(update)id: {}, type: {}", id, type);
    var itemType = itemTypeService.update(id, type);
    itemTypeFieldService.deleteByTypeId(id);
    for (String value : listField) {
      itemTypeFieldService.create(id, value);
    }
    return ItemTypeDetailResponse.from(itemType, listField);
  }
}
