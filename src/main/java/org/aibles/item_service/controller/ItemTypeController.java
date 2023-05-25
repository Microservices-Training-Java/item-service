package org.aibles.item_service.controller;

import lombok.extern.slf4j.Slf4j;
import org.aibles.item_service.dto.request.ItemTypeCreateRequest;
import org.aibles.item_service.dto.request.ItemTypeUpdateRequest;
import org.aibles.item_service.dto.response.Response;
import org.aibles.item_service.facade.ItemFacadeService;
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
@RequestMapping("api/v1/item-types")
public class ItemTypeController {

  private final ItemTypeService service;
  private final ItemFacadeService itemFacadeService;

  public ItemTypeController(ItemTypeService service, ItemFacadeService itemFacadeService) {
    this.service = service;
    this.itemFacadeService = itemFacadeService;
  }

  @PostMapping()
  @ResponseStatus(HttpStatus.CREATED)
  public Response create(@Validated @RequestBody ItemTypeCreateRequest request) {
    log.info("(create)type: {}, field: {}", request.getType(), request.getListField());
    return Response.of(
        HttpStatus.CREATED.value(),
        itemFacadeService.create(request.getType(), request.getListField())
    );
  }

  @DeleteMapping(path =  "/{id}")
  @ResponseStatus(HttpStatus.OK)
  public Response delete(@PathVariable("id") String id) {
    log.info("(delete)id: {}",id);
    return Response.of(
        HttpStatus.OK.value(),
        itemFacadeService.deleteById(id)
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
    log.info("(getById)id: {}", id);
    return Response.of(
        HttpStatus.OK.value(),
        itemFacadeService.getById(id));
  }

  @PutMapping(path =  "/{id}")
  @ResponseStatus(HttpStatus.OK)
  public Response update(@PathVariable("id") String id,
      @Validated @RequestBody ItemTypeUpdateRequest request) {
    log.info("(update)id: {}, type: {}, field: {}", id, request.getType(), request.getListField());
    return Response.of(
        HttpStatus.OK.value(),
        itemFacadeService.update(id, request.getType(), request.getListField())
    );
  }

}
