package org.aibles.item_service.facade;

import org.aibles.item_service.dto.request.CategoryCreateRequest;
import org.aibles.item_service.dto.response.CategoryResponse;
import org.aibles.item_service.dto.response.ItemCategoryDetailResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CategoryFacadeService {

  /**
   * method creates category
   * @param userId - id of category creator
   * @param request - information to create category
   * @return - category information
   */
  CategoryResponse create(String userId, CategoryCreateRequest request);

  /**
   * The function retrieves item information of the category
   * @param categoryId - categoryId needs to retrieve items
   * @param pageable - the page number and page size
   * @return item information in page format
   */
  Page<ItemCategoryDetailResponse> getItemCategories(String categoryId, Pageable pageable);
}
