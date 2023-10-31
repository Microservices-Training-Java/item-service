package org.aibles.item_service.service;

import org.aibles.item_service.dto.request.CategoryCreateRequest;
import org.aibles.item_service.dto.response.CategoryResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CategoryService {

  /**
   * method creates category
   * @param request - information to create category
   * @return - category information
   */
  CategoryResponse create(CategoryCreateRequest request);

  /**
   * list category parent_id = null
   * @param pageable - the page number and page size
   * @return list category
   */
  Page<CategoryResponse> listCategory(Pageable pageable);

  /**
   * function to check whether categoryId exists or not
   * @param categoryId - categoryId to check
   */
  void validateCategoryId(String categoryId);
}
