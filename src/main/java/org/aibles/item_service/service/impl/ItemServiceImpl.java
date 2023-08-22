package org.aibles.item_service.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.aibles.item_service.dto.request.CalculateOrderRequest;
import org.aibles.item_service.dto.response.CalculateOrderResponse;
import org.aibles.item_service.dto.response.DetailResponse;
import org.aibles.item_service.dto.response.ItemResponse;
import org.aibles.item_service.entity.Item;
import org.aibles.item_service.exception.DuplicateKeyException;
import org.aibles.item_service.exception.NotFoundException;
import org.aibles.item_service.repository.ItemRepository;
import org.aibles.item_service.repository.ValueProjection;
import org.aibles.item_service.service.ItemService;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestTemplate;

@Slf4j
public class ItemServiceImpl implements ItemService {

  private final ItemRepository repository;

  @LoadBalanced
  private final RestTemplate restTemplate;

  public ItemServiceImpl(ItemRepository repository, RestTemplate restTemplate) {
    this.repository = repository;
    this.restTemplate = restTemplate;
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
  public DetailResponse getItem(Set<String> itemIds) {
    log.info("(getItem)itemIds: {}", itemIds);
    var itemDetails = new ArrayList<Map<String, Object>>();
    Map<String, Object> itemDetail;
    List<ValueProjection> itemValueProjections;
    for (var itemId : itemIds) {
      itemValueProjections = repository.findItemDetailByItemId(itemId);
      if (!CollectionUtils.isEmpty(itemValueProjections)) {
        itemDetail = new HashMap<>();
        itemDetail.put("id", itemId);
        for (var value : itemValueProjections) {
          itemDetail.put(value.getFieldName(), value.getValue());
        }
        itemDetails.add(itemDetail);
      }
    }
    return DetailResponse.of(itemDetails);
  }

  @Override
  @Transactional
  public ItemResponse updateById(String id, String itemTypeId) {
    log.info("(updateById)id: {}, itemTypeId: {}", id, itemTypeId);
    var item = repository
        .findById(id)
        .orElseThrow(() -> {
          log.error("(updateById)id : {} --> NOT FOUND EXCEPTION", id);
          throw new NotFoundException(id, Item.class.getSimpleName());
        });
    item.setId(id);
    item.setItemTypeId(itemTypeId);
    return ItemResponse.from(item);
  }

  @Override
  public CalculateOrderResponse calculateOrder(CalculateOrderRequest request) {
    log.info("(calculateOrder)request : {}", request);
    /**
     * 1. Gọi đến api getOrderDetail của orderController để lấy thông tin của một order
     * 2. Khởi tạo 1 biến là totalAmount = 0
     * 3. dùng vòng for đi qua từng phần tử và tính theo công thức totalAmount = totalAmount + (price * quantity)
     * 4. Tạo 1 calculateOrderResponse với totalAmount và orderId để return
     */
    return null;
  }
}
