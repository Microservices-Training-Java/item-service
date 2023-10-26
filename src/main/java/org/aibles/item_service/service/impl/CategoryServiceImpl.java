package org.aibles.item_service.service.impl;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.aibles.item_service.dto.request.CategoryCreateRequest;
import org.aibles.item_service.dto.response.CategoryResponse;
import org.aibles.item_service.entity.Category;
import org.aibles.item_service.exception.CategoryNameAlreadyExitException;
import org.aibles.item_service.exception.ParentIdNotFoundException;
import org.aibles.item_service.exception.UserServiceException;
import org.aibles.item_service.repository.CategoryRepository;
import org.aibles.item_service.service.CategoryService;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

@Slf4j
public class CategoryServiceImpl implements CategoryService {

  private final CategoryRepository repository;
  @LoadBalanced
  private final RestTemplate restTemplate;
  private static final String USER_API_URL = "http://user-service/api/v1/users";

  public CategoryServiceImpl(CategoryRepository repository, RestTemplate restTemplate) {
    this.repository = repository;
    this.restTemplate = restTemplate;
  }

  @Override
  @Transactional
  public CategoryResponse create(String userId, CategoryCreateRequest request) {
    log.info("(create)userId: {}, request: {}", userId, request);
    getUserDetail(userId);
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

  @Override
  @Transactional
  public String getUserDetail(String userId) {
    log.info("(getUserDetail)userId: {}", userId);
    String CUSTOMER_GET_DETAIL_API = USER_API_URL + '/' + userId;
    try {
      ResponseEntity<String> response =
          restTemplate.exchange(CUSTOMER_GET_DETAIL_API, HttpMethod.GET, null, String.class);
      return response.getBody();
    } catch (UserServiceException ex) {
      log.error("Error calling user service", ex);
      return String.valueOf(new ResponseEntity<>("User_id not found", HttpStatus.NOT_FOUND));
    }
  }

  @Override
  @Transactional
  public void validateExistsCategoryName(String categoryName) {
    log.info("(validateExistsCategoryName)categoryName: {}", categoryName);
    if(repository.existsByCategoryName(categoryName)) {
      log.error("(validateExistsCategoryName)categoryName: {}", categoryName);
      throw new CategoryNameAlreadyExitException();
    }
  }

  @Override
  @Transactional
  public void validateParentId(String parentId) {
    log.info("(validateParentId)parentId: {}", parentId);
    if(repository.existsById(parentId)) {
      log.error("(validateParentId)parentId: {}", parentId);
      throw new ParentIdNotFoundException(parentId);
    }
  }
}
