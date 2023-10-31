package org.aibles.item_service.service;

import java.util.List;
import org.aibles.item_service.dto.request.CategoryItemCreateRequest;
import org.aibles.item_service.dto.response.CategoryItemResponse;

public interface CategoryItemService {
    CategoryItemResponse create(CategoryItemCreateRequest categoryItemCreateRequest, String userId);

    void checkCategoryId(String categoryId);

    void checkItemId(String itemId);

    /**
     * The function retrieves itemIds based on categoryId
     * @param categoryId - categoryId to be able to retrieve itemIds
     * @return list of itemIds
     */
    List<String> findItemIdByCategoryId(String categoryId);
}
