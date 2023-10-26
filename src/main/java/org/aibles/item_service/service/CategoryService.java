package org.aibles.item_service.service;

import org.aibles.item_service.dto.request.CategoryCreateRequest;
import org.aibles.item_service.dto.response.CategoryResponse;

public interface CategoryService {

  CategoryResponse create(String userId, CategoryCreateRequest request);

  String getUserDetail(String userId);
  void validateExistsCategoryName(String categoryName);
  void validateParentId(String parentId);
}
