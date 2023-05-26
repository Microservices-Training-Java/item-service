package org.aibles.item_service.facade;

import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.aibles.item_service.dto.response.ItemDetailResponse;
import org.aibles.item_service.service.ItemFieldService;
import org.aibles.item_service.service.ItemFieldValueService;
import org.aibles.item_service.service.ItemService;
import org.aibles.item_service.service.ItemTypeService;

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
  public ItemDetailResponse create(String itemTypeId, Map<String, String> fieldValue) {
    log.info("(create)itemTypeId: {}, fieldValue: {}", itemTypeId, fieldValue);
    itemTypeService.existsById(itemTypeId);
    var item = itemService.create(itemTypeId);

    for(Map.Entry<String, String> valueByField : fieldValue.entrySet()) {
      String key = valueByField.getKey();
      String value = valueByField.getValue();
      itemFieldService.existsById(key);
      itemFieldValueService.create(item.getId(), key, value);
    }

    return ItemDetailResponse.from(item, fieldValue);
  }
}
