package org.aibles.item_service.controller;

import static org.aibles.item_service.constant.ItemApiConstant.BaseUrl.ITEM_BASE_URL;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aibles.item_service.dto.request.CalculateOrderRequest;
import org.aibles.item_service.dto.request.ItemIdsRequest;
import org.aibles.item_service.dto.response.Response;
import org.aibles.item_service.service.ItemService;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;

@RestController
@Slf4j
@RequestMapping(ITEM_BASE_URL)
@RequiredArgsConstructor
public class ItemController {

  private final ItemService service;

  @LoadBalanced
  private final RestTemplate restTemplate;

  @GetMapping()
  @ResponseStatus(HttpStatus.OK)
  public Response searchItems(@Validated @RequestBody ItemIdsRequest itemIdsRequest) {
    log.info("(searchItems)itemIdsRequest: {}", itemIdsRequest.getItemIds());
    return Response.of(
        HttpStatus.OK.value(), service.getItem((itemIdsRequest.getItemIds())));
  }

  @PostMapping("/api/v1/items/calculate")
  @ResponseStatus(HttpStatus.OK)
  public Response calculate(@Valid @RequestBody CalculateOrderRequest request) {
    log.info("(calculate)request : {}", request);
    return Response.of(
            HttpStatus.OK.value(), service.calculateOrder(request));
  }

  @GetMapping("/test-rest")
  @ResponseStatus(HttpStatus.OK)
  public String test() {
    String hello = restTemplate.getForObject("http://order-service/api/v1/orders/hello", String.class);
    return hello;
  }
}
