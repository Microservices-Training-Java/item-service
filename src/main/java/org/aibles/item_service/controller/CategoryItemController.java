package org.aibles.item_service.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aibles.item_service.dto.response.Response;
import org.aibles.item_service.service.CategoryItemService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import static org.aibles.item_service.constant.ItemApiConstant.BaseUrl.CATEGORY_BASE_URL;

@RestController
@Slf4j
@RequestMapping(CATEGORY_BASE_URL)
@RequiredArgsConstructor
public class CategoryItemController {

    private final CategoryItemService service;
    @PostMapping("/{categoryId}/items/{itemId}")
    @ResponseStatus(HttpStatus.OK)
    public Response create(@Valid @RequestHeader("user_id") String userId,
                           @PathVariable("categoryId") String categoryId,
                           @PathVariable("itemId") String itemId){
        log.info("(create)userId: {}, categoryId: {}, itemId: {}", userId, categoryId, itemId);
        return Response.of(
                HttpStatus.OK.value(), service.create(categoryId,itemId ,userId));
    }

    @DeleteMapping("/{categoryId}/items/{itemId}")
    @ResponseStatus(HttpStatus.OK)
    public Response delete( @PathVariable("categoryId") String categoryId,
                            @PathVariable("itemId") String itemId) {
        log.info("(delete)categoryId: {}, itemId: {}",categoryId,itemId);
        service.delete(categoryId,itemId);
        return Response.of(
                HttpStatus.OK.value());
    }
}
