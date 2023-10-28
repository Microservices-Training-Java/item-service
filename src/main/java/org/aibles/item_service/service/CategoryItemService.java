package org.aibles.item_service.service;

import org.aibles.item_service.dto.request.CategoryItemCreateRequest;
import org.aibles.item_service.dto.response.CategoryItemResponse;

public interface CategoryItemService {
    CategoryItemResponse create(CategoryItemCreateRequest categoryItemCreateRequest, String userId);

    void checkCategoryId(String categoryId);

    void checkItemId(String itemId);

    void deleteCategoryItem(String id);
}
