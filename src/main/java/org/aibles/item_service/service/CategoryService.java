package org.aibles.item_service.service;

import org.aibles.item_service.dto.request.CategoryCreateRequest;
import org.aibles.item_service.dto.response.CategoryResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CategoryService {

  CategoryResponse create(String userId, CategoryCreateRequest request);

  String getUserDetail(String userId);
  void validateExistsCategoryName(String categoryName);
  void validateParentId(String parentId);

  /**
   * list category parent_id = null
   * @param pageable - the page number and page size
   * @return list category
   */
  Page<CategoryResponse> listCategory(Pageable pageable);
}
