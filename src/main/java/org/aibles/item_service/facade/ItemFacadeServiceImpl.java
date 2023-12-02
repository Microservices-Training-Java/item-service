package org.aibles.item_service.facade;

import lombok.extern.slf4j.Slf4j;
import org.aibles.item_service.client.service.CustomerClient;
import org.aibles.item_service.dto.ItemFieldValueDto;
import org.aibles.item_service.dto.response.DetailResponse;
import org.aibles.item_service.dto.response.ItemDetailResponse;
import org.aibles.item_service.dto.response.ItemFieldValueResponse;
import org.aibles.item_service.dto.response.ItemResponse;
import org.aibles.item_service.entity.Item;
import org.aibles.item_service.exception.ItemNameNotFoundException;
import org.aibles.item_service.exception.NotFoundException;
import org.aibles.item_service.service.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Slf4j
public class ItemFacadeServiceImpl implements ItemFacadeService {

  private final ItemTypeService itemTypeService;
  private final ItemFieldService itemFieldService;
  private final ItemService itemService;
  private final ItemFieldValueService itemFieldValueService;
  private final CustomerClient customerClient;
  private final ReviewService reviewService;

  public ItemFacadeServiceImpl(
          ItemTypeService itemTypeService,
          ItemFieldService itemFieldService,
          ItemService itemService,
          ItemFieldValueService itemFieldValueService, CustomerClient customerClient, ReviewService reviewService) {
    this.itemTypeService = itemTypeService;
    this.itemFieldService = itemFieldService;
    this.itemService = itemService;
    this.itemFieldValueService = itemFieldValueService;
    this.customerClient = customerClient;
    this.reviewService = reviewService;
  }

  @Override
  @Transactional
  public ItemDetailResponse create(String itemTypeId, List<ItemFieldValueDto> fieldValue) {
    log.info("(create)itemTypeId: {}, fieldValue: {}", itemTypeId, fieldValue);
    itemTypeService.validateExistsItemTypeId(itemTypeId);
    var item = itemService.create(itemTypeId);

    for (ItemFieldValueDto valueByField : fieldValue) {
      itemFieldService.validateExistsFieldId(valueByField.getFieldId());
      itemFieldValueService.create(item.getId(), valueByField.getFieldId(),
          valueByField.getValue());
    }

    return ItemDetailResponse.from(item, fieldValue);
  }


  @Override
  @Transactional
  public void deleteById(String id, String itemTypeId) {
    log.info("(deleteById)id: {}", id);
    itemFieldValueService.deleteByItemId(id);
    itemService.deleteById(id);
  }

  @Override
  @Transactional
  public void deleteAllByItemTypeId(String itemTypeId) {
    log.info("(deleteAllByItemTypeId)itemTypeId: {}", itemTypeId);
    var item = itemService.getAllByItemTypeId(itemTypeId);
    for (ItemResponse value : item) {
      itemFieldValueService.deleteByItemId(value.getId());
    }
    itemService.deleteAllByItemTypeId(itemTypeId);
  }

  @Override
  @Transactional(readOnly = true)
  public ItemDetailResponse getById(String id, String itemTypeId) {
    log.info("(getById)id: {}", id);
    var item = itemService.getById(id);
    if (!item.getItemTypeId().equals(itemTypeId)) {
      log.error("(getById)itemTypeId: {} --> NOT FOUND EXCEPTION", itemTypeId);
      throw new NotFoundException(itemTypeId, Item.class.getSimpleName());
    }
    var itemFieldValue = itemFieldValueService.getAllByItemId(id);

    List<ItemFieldValueDto> itemFieldValueDtos = new ArrayList<>();
    for (ItemFieldValueResponse value : itemFieldValue) {
      ItemFieldValueDto request = new ItemFieldValueDto();
      request.setFieldId(value.getFieldId());
      request.setValue(value.getValue());
      itemFieldValueDtos.add(request);
    }
    return ItemDetailResponse.from(item, itemFieldValueDtos);
  }

  @Override
  @Transactional
  public ItemDetailResponse update(String id, String itemTypeId,
      List<ItemFieldValueDto> fieldValue) {
    log.info("(update)id: {}, itemTypeId: {}, fieldValue: {}", id, itemTypeId, fieldValue);
    itemTypeService.validateExistsItemTypeId(itemTypeId);
    var item = itemService.updateById(id, itemTypeId);

    itemFieldValueService.deleteByItemId(item.getId());
    for (ItemFieldValueDto valueByField : fieldValue) {
      itemFieldService.validateExistsFieldId(valueByField.getFieldId());
      itemFieldValueService.create(item.getId(), valueByField.getFieldId(),
          valueByField.getValue());
    }

    return ItemDetailResponse.from(item, fieldValue);
  }

  @Override
  public Page<Map<String, Object>> searchItemByName(String name, int pageNum, int pageSize) {
    log.info("(searchItemByName)name : {}", name);

    DetailResponse response;
    if(name == null){
      response = itemService.getItem(itemService.getAllItemId());
    }
    else {
      Set<String> itemIds = itemService.getItemIdByName(name);
      if (itemIds.isEmpty()) {
        throw new ItemNameNotFoundException(name);
      }
      response = itemService.getItem(itemIds);
    }

    Pageable pageable = PageRequest.of(pageNum - 1, pageSize);
    Page<Map<String, Object>> page = new PageImpl<>(response.getItems(), pageable, response.getItems().size());

    return page;
  }

  @Override
  public void deleteReview(String customerId, String itemId, String reviewId) {
    log.info("(deleteReview)customerId: {}, itemId: {}, reviewId: {}", customerId, itemId, reviewId);
    customerClient.getCustomerDetail(customerId);
    reviewService.validateItemId(itemId);
    reviewService.validateReviewId(reviewId);
  }
}
