package org.aibles.item_service.controller;

import static org.aibles.item_service.constant.ItemApiConstant.BaseUrl.ITEM_BASE_URL;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aibles.item_service.dto.request.ItemCalculateRequest;
import org.aibles.item_service.dto.request.ItemIdsRequest;
import org.aibles.item_service.dto.response.Response;
import org.aibles.item_service.service.ItemService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping(ITEM_BASE_URL)
@RequiredArgsConstructor
public class ItemController {

  private final ItemService service;

  @GetMapping()
  @ResponseStatus(HttpStatus.OK)
  public Response searchItems(@Validated @RequestBody ItemIdsRequest itemIdsRequest) {
    log.info("(searchItems)itemIdsRequest: {}", itemIdsRequest.getItemIds());
    return Response.of(
        HttpStatus.OK.value(), service.getItem((itemIdsRequest.getItemIds())));
  }
  @PostMapping("/calculate")
  @ResponseStatus(HttpStatus.OK)
  public ResponseEntity<Response> getOrderDetail( @RequestBody ItemCalculateTotalRequest request) {
    String orderDetail = service.getOrderDetail(request.getOrderId());
    return ResponseEntity.ok(Response.of(HttpStatus.OK.value(), orderDetail));
  }
  @PostMapping("/api/v1/items/calculate")
  @ResponseStatus(HttpStatus.OK)
  public Response calculate(@Valid @RequestBody ItemCalculateRequest request) {
    log.info("(calculate)request : {}", request);
    return Response.of(
        HttpStatus.OK.value(), service.calculateOrder(request));
  }

}
