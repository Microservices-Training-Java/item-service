package org.aibles.item_service.facade;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aibles.item_service.client.service.UserClient;
import org.aibles.item_service.dto.request.CategoryCreateRequest;
import org.aibles.item_service.dto.response.CategoryResponse;
import org.aibles.item_service.service.CategoryService;

@Slf4j
@RequiredArgsConstructor
public class CategoryFacadeServiceImpl implements CategoryFacadeService {

  private final UserClient userClient;
  private final CategoryService categoryService;

  @Override
  public CategoryResponse create(String userId, CategoryCreateRequest request) {
    log.info("(create)userId: {}, request: {}", userId, request);
    //Call the user-service side to see if it is a user or not
    userClient.getUserDetail(userId);
    return categoryService.create(request);
  }
}
