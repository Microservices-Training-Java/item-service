package org.aibles.item_service.service.impl;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.aibles.item_service.dto.request.CategoryCreateRequest;
import org.aibles.item_service.dto.response.CategoryResponse;
import org.aibles.item_service.dto.response.ItemCategoryDetailResponse;
import org.aibles.item_service.entity.Category;
import org.aibles.item_service.exception.CategoryIdNotFoundException;
import org.aibles.item_service.exception.CategoryNameAlreadyExitException;
import org.aibles.item_service.exception.ParentIdNotFoundException;
import org.aibles.item_service.repository.CategoryRepository;
import org.aibles.item_service.service.CategoryService;
import org.springframework.dao.DuplicateKeyException;
import org.aibles.item_service.exception.UserServiceException;
import org.aibles.item_service.repository.CategoryRepository;
import org.aibles.item_service.service.CategoryService;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

@Slf4j
public class CategoryServiceImpl implements CategoryService {

  private final CategoryRepository repository;

  public CategoryServiceImpl(CategoryRepository repository) {
    this.repository = repository;
  }

  @Override
  @Transactional
  public CategoryResponse create(CategoryCreateRequest request) {
    log.info("(create)request: {}", request);
    validateExistsCategoryName(request.getCategoryName());
    if(!request.getParentId().isEmpty()) {
      validateParentId(request.getParentId());
    }
    try {
      return CategoryResponse.from(
          repository.save(
              Category.of(request.getCategoryName(),
                  request.getParentId(),
                  request.getDescription())));
    } catch (DuplicateKeyException er) {
      log.error("(create)exception duplicate: {}", er.getClass().getName());
      throw new DuplicateKeyException(er.getClass().getName());
    }
  }

  private void validateExistsCategoryName(String categoryName) {
    log.info("(validateExistsCategoryName)categoryName: {}", categoryName);
    if(repository.existsByCategoryName(categoryName)) {
      log.error("(validateExistsCategoryName)categoryName: {}", categoryName);
      throw new CategoryNameAlreadyExitException();
    }
  }

  private void validateParentId(String parentId) {
    log.info("(validateParentId)parentId: {}", parentId);
    if(!repository.existsById(parentId)) {
      log.error("(validateParentId)parentId: {}", parentId);
      throw new ParentIdNotFoundException();
    }
  }

  @Override
  public void validateCategoryId(String categoryId) {
    log.info("(validateCategoryId)categoryId: {}", categoryId);
    if(!repository.existsById(categoryId)) {
      log.error("(validateCategoryId)categoryId: {}", categoryId);
      throw new CategoryIdNotFoundException(categoryId);
    }
  }

  @Override
  public Page<CategoryResponse> listCategory(Pageable pageable) {
    Page<Category> categories = repository.findAllByParentIdNull(pageable);
    return categories.map(CategoryResponse::from);
  }
}
