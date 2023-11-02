package org.aibles.item_service.controller;

import static org.aibles.item_service.constant.ItemApiConstant.BaseUrl.CATEGORY_BASE_URL;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aibles.item_service.dto.request.CategoryCreateRequest;
import org.aibles.item_service.dto.response.ItemCategoryDetailResponse;
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
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping(CATEGORY_BASE_URL)
@RequiredArgsConstructor
public class CategoryController {

  private final CategoryFacadeService service;
  private final CategoryService categoryService;

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
    final Page<CategoryResponse> categoryResponses = categoryService.list(pagingReq.makePageable());
    return Response.of(HttpStatus.OK.value(), PagingRes.of(categoryResponses));
  }

  @GetMapping("/{category_id}/items")
  @ResponseStatus(HttpStatus.OK)
  public Response listItem(@Validated() final PagingReq pagingReq,
      @PathVariable(name = "category_id") String categoryId){
    log.info("(listItem)category: {}, pagingReq: {}", categoryId, pagingReq);
    final Page<ItemCategoryDetailResponse> categoryResponses = service.getItemCategories(categoryId, pagingReq.makePageable());
    return Response.of(HttpStatus.OK.value(), PagingRes.of(categoryResponses));
  }

  @DeleteMapping("/{category_id}")
  @ResponseStatus(HttpStatus.OK)
  public Response delete(@Valid @RequestHeader("user_id") String userId,@PathVariable(name = "category_id") String categoryId) {
    log.info("(delete)categoryId: {}, userId: {}", categoryId, userId);
    service.delete(categoryId, userId);
    return Response.of(HttpStatus.OK.value());
  }
}
