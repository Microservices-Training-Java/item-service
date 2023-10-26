package org.aibles.item_service.service;

import org.aibles.item_service.dto.request.CategoryCreateRequest;
import org.aibles.item_service.dto.response.CategoryResponse;

public interface CategoryService {

  /**
   * method creates category
   * @param userId - id of category creation manager
   * @param request - information to create category
   * @return - category information
   */
  CategoryResponse create(String userId, CategoryCreateRequest request);

  /**
   * method call user-service to check whether that user id exists or not
   * @param userId - id to check
   */
  void getUserDetail(String userId);

  /**
   * method checks whether category name exists or not
   * @param categoryName - category name to check
   */
  void validateExistsCategoryName(String categoryName);

  /**
   * method checks whether parent id exists or not
   * @param parentId - parentId to check
   */
  void validateParentId(String parentId);
}
