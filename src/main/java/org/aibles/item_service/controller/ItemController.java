package org.aibles.item_service.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aibles.item_service.dto.request.ItemCalculateRequest;
import org.aibles.item_service.dto.request.ItemIdsRequest;
import org.aibles.item_service.dto.response.Response;
import org.aibles.item_service.facade.ItemFacadeService;
import org.aibles.item_service.service.ItemService;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static org.aibles.item_service.constant.ItemApiConstant.BaseUrl.ITEM_BASE_URL;

@RestController
@Slf4j
@RequestMapping(ITEM_BASE_URL)
@RequiredArgsConstructor
public class ItemController {

  private final ItemService service;
  private final ItemFacadeService facadeService;

  @GetMapping()
  @ResponseStatus(HttpStatus.OK)
  public Response searchItems(@Validated @RequestBody ItemIdsRequest itemIdsRequest) {
    log.info("(searchItems)itemIdsRequest: {}", itemIdsRequest.getItemIds());
    return Response.of(
        HttpStatus.OK.value(), service.getItem((itemIdsRequest.getItemIds())));
  }
  @PostMapping("/calculate")
  @ResponseStatus(HttpStatus.OK)
  public Response calculate(@Valid @RequestBody ItemCalculateRequest request) {
    log.info("(calculate)request : {}", request);
    return Response.of(
        HttpStatus.OK.value(), service.calculateOrder(request));
  }

  @GetMapping("/price/{itemId}")
  public String getPrice(@PathVariable("itemId") String itemId) {
    log.info("(getPrice)itemId: {}", itemId);
    return service.getPriceItem(itemId);
  }

  @GetMapping("/search")
  public Response searchItem(@RequestParam String name){
    log.info("(searchItem)name: {}", name);
    return Response.of(
            HttpStatus.OK.value(), facadeService.searchItemByName(name));
  }


}
