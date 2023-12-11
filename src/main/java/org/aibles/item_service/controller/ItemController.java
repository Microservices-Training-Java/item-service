package org.aibles.item_service.controller;

import static org.aibles.item_service.constant.ItemApiConstant.BaseUrl.ITEM_BASE_URL;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aibles.item_service.dto.request.ItemCalculateRequest;
import org.aibles.item_service.dto.request.ItemIdsRequest;
import org.aibles.item_service.dto.response.ItemResponse;
import org.aibles.item_service.dto.response.Response;
import org.aibles.item_service.facade.ItemFacadeService;
import org.aibles.item_service.paging.PagingReq;
import org.aibles.item_service.service.ItemService;
import org.aibles.item_service.service.ReviewService;
import org.springframework.data.domain.Page;
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
  private final ReviewService reviewService;

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
  public Response searchItem(@RequestParam(required = false) String name,
                             @Validated() final PagingReq pagingReq){
    log.info("(searchItem)name: {}, pagingReq: {}", name, pagingReq);
    return Response.of(
            HttpStatus.OK.value(), facadeService.searchItemByName(name, pagingReq.makePageable()));
  }

  @GetMapping("/{item_id}")
  public ItemResponse getItemById(@PathVariable("item_id") String itemId) {
    log.info("(getItemById)itemId: {}", itemId);
    return service.getById(itemId);
  }

  @DeleteMapping("/{item_id}/reviews/{review_id}")
  public Response deleteReview(@PathVariable("item_id") String itemId, @PathVariable("review_id") String reviewId,
                            @Validated @RequestHeader("customer_id") String customerId){
    log.info("(deleteReview)itemId: {}, reviewId: {}, customerId: {}", itemId, reviewId, customerId);
    return Response.of(
            HttpStatus.OK.value(), reviewService.deleteReview(customerId, reviewId, itemId));
  }

}
