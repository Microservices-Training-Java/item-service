package org.aibles.item_service.service;

import org.aibles.item_service.dto.request.CategoryCreateRequest;
import org.aibles.item_service.dto.response.CategoryResponse;

public interface CategoryService {

  /**
   * method creates category
   * @param request - information to create category
   * @return - category information
   */
  CategoryResponse create(CategoryCreateRequest request);
}
