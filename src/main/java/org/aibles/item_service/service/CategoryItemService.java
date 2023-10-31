package org.aibles.item_service.service;

import org.aibles.item_service.dto.request.CategoryItemCreateRequest;
import org.aibles.item_service.dto.response.CategoryItemResponse;

public interface CategoryItemService {
    CategoryItemResponse create(String categoryId, String itemId, String userId);

    void checkCategoryId(String categoryId);

    void checkItemId(String itemId);
}
