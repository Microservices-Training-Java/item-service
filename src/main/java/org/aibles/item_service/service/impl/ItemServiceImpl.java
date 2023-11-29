package org.aibles.item_service.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.aibles.item_service.client.dto.ItemDto;
import org.aibles.item_service.client.service.OrderClient;
import org.aibles.item_service.dto.request.ItemCalculateRequest;
import org.aibles.item_service.dto.response.DetailResponse;
import org.aibles.item_service.dto.response.ItemResponse;
import org.aibles.item_service.dto.response.ItemTotalOrderPriceResponse;
import org.aibles.item_service.entity.Item;
import org.aibles.item_service.exception.DuplicateKeyException;
import org.aibles.item_service.exception.NotFoundException;
import org.aibles.item_service.repository.ItemFieldValueRepository;
import org.aibles.item_service.repository.ItemRepository;
import org.aibles.item_service.repository.ValueProjection;
import org.aibles.item_service.service.ItemService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
public class ItemServiceImpl implements ItemService {

  private final ItemRepository repository;

  private final ItemFieldValueRepository itemFieldValueRepository;
  private final OrderClient orderClient;

  public ItemServiceImpl(ItemRepository repository, ItemFieldValueRepository itemFieldValueRepository, OrderClient orderClient) {
    this.repository = repository;
    this.itemFieldValueRepository = itemFieldValueRepository;
    this.orderClient = orderClient;
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
  public ItemTotalOrderPriceResponse calculateOrder(ItemCalculateRequest request) {
    log.info("(calculateOrder)request: {}", request);
    // Khởi tạo biến totalAmount là 0 để tính tổng đơn hàng
    List<ItemDto> itemList = orderClient.getOrderDetail(request.getOrderId()).getItems();
    double totalAmount = 0;
    // Tính tổng đơn hàng
    // TODO : add price in response of getOrderDetail API in order-service
    for (ItemDto itemDto : itemList) {
      double price = Double.parseDouble(itemFieldValueRepository.getPriceValueByItemId(itemDto.getItemId()).orElse("0"));
      totalAmount = totalAmount + (itemDto.getQuantity() * price);
    }
    ItemTotalOrderPriceResponse response = new ItemTotalOrderPriceResponse();
    response.setTotalAmount(totalAmount);
    response.setOrderId(request.getOrderId());
    return response;
  }

  @Override
  @Transactional(readOnly = true)
  public String getPriceItem(String itemId) {
    log.info("(getPriceItem)itemId: {}", itemId);
    String price = itemFieldValueRepository.getPriceValueByItemId(itemId).orElse("0");
    return price;
  }

  @Override
  @Transactional(readOnly = true)
  public String getValueItemByItemIdAndName(String itemId, String name) {
    log.info("(getValueItemByItemId)itemId: {}, name: {}", itemId, name);
    return itemFieldValueRepository.getValueItemByItemIdAndName(itemId, name);
  }

  @Override
  public Set<String> getItemIdByName(String name) {
    log.info("(getItemIdByName)name :{}", name);
    return repository.getItemIdByName(name);
  }

  @Override
  public Set<String> getAllItemId() {
    log.info("(getAllItemId)");
    return repository.getAllItemId();
  }
}


