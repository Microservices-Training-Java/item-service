package org.aibles.item_service.controller;

import static org.aibles.item_service.constant.ItemApiConstant.BaseUrl.TYPE_BASE_URL;
import static org.aibles.item_service.constant.ItemApiConstant.ResourceConstant.FIELD;
import static org.aibles.item_service.constant.ItemApiConstant.ResourceConstant.ITEM;

import lombok.extern.slf4j.Slf4j;
import org.aibles.item_service.dto.request.ItemCreateRequest;
import org.aibles.item_service.dto.request.ItemTypeCreateRequest;
import org.aibles.item_service.dto.request.ItemTypeUpdateRequest;
import org.aibles.item_service.dto.request.ItemUpdateRequest;
import org.aibles.item_service.dto.response.Response;
import org.aibles.item_service.facade.ItemFacadeService;
import org.aibles.item_service.facade.ItemTypeFacadeService;
import org.aibles.item_service.service.ItemTypeService;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping(TYPE_BASE_URL)
public class ItemTypeController {

  private final ItemTypeService service;
  private final ItemFacadeService itemFacadeService;
  private final ItemTypeFacadeService itemTypeFacadeService;

  public ItemTypeController(ItemTypeService service, ItemFacadeService itemFacadeService,
      ItemTypeFacadeService itemTypeFacadeService) {
    this.service = service;
    this.itemFacadeService = itemFacadeService;
    this.itemTypeFacadeService = itemTypeFacadeService;
  }

  @PostMapping()
  @ResponseStatus(HttpStatus.CREATED)
  public Response create(@Validated @RequestBody ItemTypeCreateRequest request) {
    log.info("(create)type: {}, field: {}", request.getType(), request.getListField());
    return Response.of(
        HttpStatus.CREATED.value(),
        itemTypeFacadeService.create(request.getType(), request.getListField())
    );
  }

  @DeleteMapping(path =  "/{id}")
  @ResponseStatus(HttpStatus.OK)
  public Response delete(@PathVariable("id") String id) {
    log.info("(delete)id: {}",id);
    itemTypeFacadeService.deleteById(id);
    return Response.of(
        HttpStatus.OK.value()
    );
  }

  @GetMapping()
  @ResponseStatus(HttpStatus.OK)
  public Response getAll() {
    log.info("(getAll)item type");
    return Response.of(
        HttpStatus.OK.value(),
        service.getAll());
  }

  @GetMapping(path =  "/{id}")
  @ResponseStatus(HttpStatus.OK)
  public Response get(@PathVariable("id") String id) {
    log.info("(get)id: {}", id);
    return Response.of(
        HttpStatus.OK.value(),
        itemTypeFacadeService.getById(id));
  }

  @GetMapping(path =  {"/{id}" + FIELD})
  @ResponseStatus(HttpStatus.OK)
  public Response getFieldIdById(@PathVariable("id") String id) {
    log.info("(getFieldIdById)id: {}", id);
    return Response.of(
        HttpStatus.OK.value(),
        itemTypeFacadeService.getFieldById(id));
  }

  @PutMapping(path =  "/{id}")
  @ResponseStatus(HttpStatus.OK)
  public Response update(@PathVariable("id") String id,
      @Validated @RequestBody ItemTypeUpdateRequest request) {
    log.info("(update)id: {}, type: {}, field: {}", id, request.getType(), request.getListField());
    return Response.of(
        HttpStatus.OK.value(),
        itemTypeFacadeService.update(id, request.getType(), request.getListField())
    );
  }

  @PostMapping(path = {"/{item_type_id}" + ITEM})
  @ResponseStatus(HttpStatus.CREATED)
  public Response createItem(@PathVariable("item_type_id") String itemTypeId,
      @Validated @RequestBody ItemCreateRequest request) {
    log.info("(createItem)itemTypeId: {}, value: {}", itemTypeId, request.getValue());
    return Response.of(
        HttpStatus.CREATED.value(),
        itemFacadeService.create(itemTypeId, request.getValue())
    );
  }

  @DeleteMapping(path =  {"/{item_type_id}" + ITEM + "/{id}"})
  @ResponseStatus(HttpStatus.OK)
  public Response deleteItem(@PathVariable("item_type_id") String itemTypeId, @PathVariable("id") String id) {
    log.info("(deleteItem)id: {}, itemTypeId: {}", id, itemTypeId);
    service.validateExistsItemTypeId(itemTypeId);
    itemFacadeService.deleteById(id, itemTypeId);
    return Response.of(
        HttpStatus.OK.value()
    );
  }

  @GetMapping(path =  {"/{item_type_id}" + ITEM + "/{id}"})
  @ResponseStatus(HttpStatus.OK)
  public Response getItem(@PathVariable("item_type_id") String itemTypeId, @PathVariable("id") String id) {
    log.info("(getItem)id: {}, itemTypeId: {}", id, itemTypeId);
    service.validateExistsItemTypeId(itemTypeId);
    return Response.of(
        HttpStatus.OK.value(),
        itemFacadeService.getById(id, itemTypeId));
  }

  @PutMapping(path =  {"/{item_type_id}" + ITEM + "/{id}"})
  @ResponseStatus(HttpStatus.OK)
  public Response updateItem(@PathVariable("item_type_id") String itemTypeId,
      @PathVariable("id") String id,
      @Validated @RequestBody ItemUpdateRequest request) {
    log.info("(updateItem)id: {}, itemTypeId: {}, value: {}", id, itemTypeId, request.getValue());
    return Response.of(
        HttpStatus.OK.value(),
        itemFacadeService.update(id, itemTypeId, request.getValue())
    );
  }

}
