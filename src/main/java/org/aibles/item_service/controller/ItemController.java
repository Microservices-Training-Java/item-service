package org.aibles.item_service.controller;

import static org.aibles.item_service.constant.ItemApiConstant.BaseUrl.TYPE_BASE_URL;
import static org.aibles.item_service.constant.ItemApiConstant.ResourceConstant.ITEM;

import lombok.extern.slf4j.Slf4j;
import org.aibles.item_service.dto.request.ItemCreateRequest;
import org.aibles.item_service.dto.request.ItemTypeCreateRequest;
import org.aibles.item_service.dto.response.Response;
import org.aibles.item_service.facade.ItemFacadeService;
import org.aibles.item_service.service.ItemService;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping(TYPE_BASE_URL)
public class ItemController {


  private final ItemFacadeService itemFacadeService;

  public ItemController(ItemFacadeService itemFacadeService) {
    this.itemFacadeService = itemFacadeService;
  }

  @PostMapping(path = {"/{item_type_id}" + ITEM})
  @ResponseStatus(HttpStatus.CREATED)
  public Response create(@PathVariable("item_type_id") String itemTypeId,
      @Validated @RequestBody ItemCreateRequest request) {
    log.info("(create)itemTypeId: {}, value: {}", itemTypeId, request.getValue());
    return Response.of(
        HttpStatus.CREATED.value(),
        itemFacadeService.create(itemTypeId, request.getValue())
    );
  }

}
