package org.aibles.item_service.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aibles.item_service.dto.request.CategoryCreateRequest;
import org.aibles.item_service.dto.request.CategoryItemCreateRequest;
import org.aibles.item_service.dto.response.Response;
import org.aibles.item_service.service.CategoryItemService;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static org.aibles.item_service.constant.ItemApiConstant.BaseUrl.CATEGORY_ITEM_BASE_URL;

@RestController
@Slf4j
@RequestMapping(CATEGORY_ITEM_BASE_URL)
@RequiredArgsConstructor
public class CategoryItemController {

    private final CategoryItemService service;
    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public Response create(@Valid @RequestHeader("user_id") String userId,
                           @Validated @RequestBody CategoryItemCreateRequest request) {
        log.info("(create)userId: {}, request: {}", userId, request);
        return Response.of(
                HttpStatus.CREATED.value(), service.create(request,userId));
    }

    @DeleteMapping("/{categoryItemId}")
    @ResponseStatus(HttpStatus.OK)
    public Response delete(@PathVariable String categoryItemId) {
        log.info("(delete)categoryItemId: {}",categoryItemId);
        service.deleteCategoryItem(categoryItemId);
        return Response.of(
                HttpStatus.OK.value());
    }
}
