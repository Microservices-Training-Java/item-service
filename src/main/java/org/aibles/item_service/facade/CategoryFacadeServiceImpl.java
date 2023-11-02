package org.aibles.item_service.facade;

import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aibles.item_service.client.service.UserClient;
import org.aibles.item_service.dto.request.CategoryCreateRequest;
import org.aibles.item_service.dto.response.CategoryResponse;
import org.aibles.item_service.dto.response.ItemCategoryDetailResponse;
import org.aibles.item_service.repository.CategoryItemRepository;
import org.aibles.item_service.service.CategoryItemService;
import org.aibles.item_service.service.CategoryService;
import org.aibles.item_service.service.ItemService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
public class CategoryFacadeServiceImpl implements CategoryFacadeService {

  private final UserClient userClient;
  private final CategoryService categoryService;
  private final CategoryItemService categoryItemService;
  private final ItemService itemService;
  private final CategoryItemRepository categoryItemRepository;

  private static final String VALUE_NAME = "name";
  private static final String VALUE_PRICE = "price";
  private static final String VALUE_DESCRIPTION = "description";
  private static final String VALUE_URL_IMAGE = "url_image";

  @Override
  public CategoryResponse create(String userId, CategoryCreateRequest request) {
    log.info("(create)userId: {}, request: {}", userId, request);
    //Call the user-service side to see if it is a user or not
    userClient.getUserDetail(userId);
    return categoryService.create(request);
  }

  @Override
  @Transactional
  public Page<ItemCategoryDetailResponse> getItemCategories(String categoryId, Pageable pageable) {
    log.info("(getItemCategories) categoryId: {}, pageable: {}", categoryId, pageable);
    categoryService.validateCategoryId(categoryId);
    var itemIds = categoryItemService.findItemIdByCategoryId(categoryId);
    List<ItemCategoryDetailResponse> list = new ArrayList<>();
    for(var itemId : itemIds) {
      String name = itemService.getValueItemByItemIdAndName(itemId, VALUE_NAME);
      double price = Double.parseDouble(itemService.getValueItemByItemIdAndName(itemId, VALUE_PRICE));
      String description = itemService.getValueItemByItemIdAndName(itemId, VALUE_DESCRIPTION);
      String urlImage = itemService.getValueItemByItemIdAndName(itemId, VALUE_URL_IMAGE);
      ItemCategoryDetailResponse response = ItemCategoryDetailResponse.from(itemId, name, description, price, urlImage, categoryId);
      list.add(response);
    }
    int start = (int) pageable.getOffset();
    int end = Math.min((start + pageable.getPageSize()), list.size());
    List<ItemCategoryDetailResponse> subList = list.subList(start, end);
    return new PageImpl<>(subList, pageable, list.size());
  }

  @Override
  @Transactional
  public void delete(String categoryId, String userId) {
    log.info("(delete)userId: {}, categoryId: {}", userId, categoryId);
    userClient.getUserDetail(userId);
    categoryItemRepository.deleteCategoryAndItems(categoryId);
    categoryService.delete(categoryId);
  }


}
