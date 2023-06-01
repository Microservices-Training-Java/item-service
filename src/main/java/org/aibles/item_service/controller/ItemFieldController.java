package org.aibles.item_service.controller;

import lombok.extern.slf4j.Slf4j;
import org.aibles.item_service.dto.request.ItemFieldCreateRequest;
import org.aibles.item_service.dto.request.ItemFieldUpdateRequest;
import org.aibles.item_service.dto.response.Response;
import org.aibles.item_service.service.ItemFieldService;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("api/v1/item-fields")
public class ItemFieldController {

  private final ItemFieldService service;

  public ItemFieldController(ItemFieldService service) {
    this.service = service;
  }

  @PostMapping()
  @ResponseStatus(HttpStatus.CREATED)
  public Response create(@Validated @RequestBody ItemFieldCreateRequest request) {
    log.info("(create)name : {}, uniqueName: {}", request.getName(), request.getUniqueName());
    return Response.of(HttpStatus.CREATED.value(), service.create(request));
  }

  @PutMapping(path = "/{id}")
  @ResponseStatus(HttpStatus.OK)
  public Response update(@PathVariable String id,
      @Validated @RequestBody ItemFieldUpdateRequest request) {
    log.info("(update)id : {}, request : {}", id, request);
    return Response.of(HttpStatus.OK.value(), service.update(id, request));
  }

}
