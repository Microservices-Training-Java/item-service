package org.aibles.item_service.controller;

import static org.aibles.item_service.constant.ItemApiConstant.BaseUrl.CATEGORY_BASE_URL;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aibles.item_service.dto.request.CategoryCreateRequest;
import org.aibles.item_service.dto.response.Response;
import org.aibles.item_service.facade.CategoryFacadeService;
import org.aibles.item_service.dto.response.CategoryResponse;
import org.aibles.item_service.dto.response.Response;
import org.aibles.item_service.paging.PagingReq;
import org.aibles.item_service.paging.PagingRes;
import org.aibles.item_service.service.CategoryService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@Slf4j
@RequestMapping(CATEGORY_BASE_URL)
@RequiredArgsConstructor
public class CategoryController {

  private final CategoryFacadeService service;
  private final CategoryService service;

  @PostMapping
  @ResponseStatus(HttpStatus.OK)
  public Response create(@Valid @RequestHeader("user_id") String userId,
      @Validated @RequestBody CategoryCreateRequest request) {
    log.info("(create)userId: {}, request: {}", userId, request);
    return Response.of(
        HttpStatus.OK.value(), service.create(userId, request));
  }

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public Response list(@Validated() final PagingReq pagingReq){
    log.info("(list)");
    final Page<CategoryResponse> categoryResponses = service.listCategory(pagingReq.makePageable());
    return Response.of(HttpStatus.OK.value(), PagingRes.of(categoryResponses));
  }
}
