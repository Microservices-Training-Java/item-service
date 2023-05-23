package org.aibles.item_service.controller;

import static org.aibles.item_service.constant.ItemApiConstant.BaseUrl.TYPE_FIELD_BASE_URL;
import static org.aibles.item_service.constant.ItemApiConstant.ResourceConstant.*;

import lombok.extern.slf4j.Slf4j;
import org.aibles.item_service.dto.request.ItemTypeCreateRequest;
import org.aibles.item_service.dto.response.Response;
import org.aibles.item_service.service.ItemTypeFieldService;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping(TYPE_FIELD_BASE_URL)
public class ItemTypeFieldController {

  private final ItemTypeFieldService service;

  public ItemTypeFieldController(ItemTypeFieldService service) {
    this.service = service;
  }

  @PostMapping(path = {"/{item_type_id}" + FIELD + "/{field_id}"})
  @ResponseStatus(HttpStatus.CREATED)
  public Response create(@PathVariable("item_type_id") String itemTypeId,
      @PathVariable("field_id") String fieldId) {
    log.info("(create)itemTypeId: {}, fieldId: {}", itemTypeId, fieldId);
    return Response.of(
        HttpStatus.CREATED.value(),
        service.create(itemTypeId, fieldId)
    );
  }

  @DeleteMapping(path =  "/{id}")
  @ResponseStatus(HttpStatus.OK)
  public Response delete(@PathVariable("id") String id) {
    log.info("(delete)id: {}",id);
    return Response.of(
        HttpStatus.CREATED.value(),
        service.deleteById(id));
  }

  @GetMapping()
  @ResponseStatus(HttpStatus.OK)
  public Response getAll() {
    log.info("(getAll)item type field");
    return Response.of(
        HttpStatus.OK.value(),
        service.getAll());
  }
  @GetMapping(path =  "/{id}")
  @ResponseStatus(HttpStatus.OK)
  public Response getById(@PathVariable("id") String id) {
    log.info("(getById)id: {}", id);
    return Response.of(
        HttpStatus.OK.value(),
        service.getById(id));
  }
}
