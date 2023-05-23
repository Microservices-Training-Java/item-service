package org.aibles.item_service.controller;

import lombok.extern.slf4j.Slf4j;
import org.aibles.item_service.dto.request.ItemTypeCreateRequest;
import org.aibles.item_service.dto.response.Response;
import org.aibles.item_service.service.ItemTypeService;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("api/v1/item-types")
public class ItemTypeController {

  private final ItemTypeService service;

  public ItemTypeController(ItemTypeService service) {
    this.service = service;
  }

  @PostMapping()
  @ResponseStatus(HttpStatus.CREATED)
  public Response create(@Validated @RequestBody ItemTypeCreateRequest request) {
    log.info("(create)type: {}", request.getType());
    return Response.of(
        HttpStatus.CREATED.value(),
        service.create(request.getType())
    );
  }

  @DeleteMapping(path =  "/{id}")
  @ResponseStatus(HttpStatus.OK)
  public String delete(@PathVariable("id") String id) {
    log.info("(delete)id: {}",id);
    return service.deleteById(id);
  }
}
