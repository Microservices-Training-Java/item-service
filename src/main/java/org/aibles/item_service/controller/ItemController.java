package org.aibles.item_service.controller;

import static org.aibles.item_service.constant.ItemApiConstant.BaseUrl.ITEM_BASE_URL;

import lombok.extern.slf4j.Slf4j;
import org.aibles.item_service.dto.request.ListItemIdRequest;
import org.aibles.item_service.dto.response.Response;
import org.aibles.item_service.facade.ItemFacadeService;
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
public class ItemController {

  private final ItemFacadeService itemFacadeService;


  public ItemController(ItemFacadeService itemFacadeService) {
    this.itemFacadeService = itemFacadeService;
  }

  @GetMapping()
  @ResponseStatus(HttpStatus.OK)
  public Response getListItem(@Validated @RequestBody ListItemIdRequest listItemIdRequest) {
    log.info("(getListItem)listItemIdRequest: {}", listItemIdRequest.getListItemId());
    return Response.of(
        HttpStatus.OK.value(), itemFacadeService.getListItems(listItemIdRequest.getListItemId()));
  }
}
