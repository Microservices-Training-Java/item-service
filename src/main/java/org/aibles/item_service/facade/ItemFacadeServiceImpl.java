package org.aibles.item_service.facade;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.aibles.item_service.dto.response.ItemDetailResponse;
import org.aibles.item_service.dto.response.ItemFieldValueResponse;
import org.aibles.item_service.dto.response.ItemResponse;
import org.aibles.item_service.dto.response.ItemTypeDetailResponse;
import org.aibles.item_service.dto.response.ItemTypeFieldResponse;
import org.aibles.item_service.entity.ItemTypeField;
import org.aibles.item_service.exception.MapNotFoundException;
import org.aibles.item_service.exception.NotFoundException;
import org.aibles.item_service.service.ItemFieldService;
import org.aibles.item_service.service.ItemFieldValueService;
import org.aibles.item_service.service.ItemService;
import org.aibles.item_service.service.ItemTypeService;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
public class ItemFacadeServiceImpl implements ItemFacadeService{

  private final ItemTypeService itemTypeService;
  private final ItemFieldService itemFieldService;
  private final ItemService itemService;
  private final ItemFieldValueService itemFieldValueService;

  public ItemFacadeServiceImpl(ItemTypeService itemTypeService, ItemFieldService itemFieldService,
      ItemService itemService,
      ItemFieldValueService itemFieldValueService) {
    this.itemTypeService = itemTypeService;
    this.itemFieldService = itemFieldService;
    this.itemService = itemService;
    this.itemFieldValueService = itemFieldValueService;
  }

  @Override
  @Transactional
  public ItemDetailResponse create(String itemTypeId, Map<String, String> fieldValue,String image_id) {
    log.info("(create)itemTypeId: {}, fieldValue: {}", itemTypeId, fieldValue);
    itemTypeService.existsById(itemTypeId);
    var item = itemService.create(itemTypeId);

    if(fieldValue == null || fieldValue.isEmpty()) {
      log.error("(create)fieldValue : {} --> NOT FOUND EXCEPTION", fieldValue);
      throw new MapNotFoundException(fieldValue);
    }

    for(Map.Entry<String, String> valueByField : fieldValue.entrySet()) {
      itemFieldService.existsById(valueByField.getKey());
      itemFieldValueService.create(item.getId(), valueByField.getKey(), valueByField.getValue(),image_id);
    }

    return ItemDetailResponse.from(item, fieldValue);
  }

}
