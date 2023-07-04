package org.aibles.item_service.controller;

import static org.aibles.item_service.constant.ItemApiConstant.BaseUrl.ITEM_BASE_URL;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aibles.item_service.dto.request.SetItemIdRequest;
import org.aibles.item_service.dto.response.Response;
import org.aibles.item_service.service.ItemService;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
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
  public Response getListItem(@Validated @RequestBody SetItemIdRequest setItemIdRequest) {
    log.info("(getListItem)listItemIdRequest: {}", setItemIdRequest.getItemIds());
    return Response.of(
        HttpStatus.OK.value(), service.getItem((setItemIdRequest.getItemIds())));
  }
}
