package org.aibles.item_service.service;

import org.aibles.item_service.dto.request.CategoryCreateRequest;
import org.aibles.item_service.dto.response.CategoryResponse;

public interface CategoryService {

  /**
   * method creates category
   * @param userId - id of category creator
   * @param request - information to create category
   * @return - category information
   */
  CategoryResponse create(String userId, CategoryCreateRequest request);
}
