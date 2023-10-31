package org.aibles.item_service.service.impl;

import jakarta.transaction.Transactional;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.aibles.item_service.client.service.UserClient;
import org.aibles.item_service.dto.request.CategoryItemCreateRequest;
import org.aibles.item_service.dto.response.CategoryItemResponse;
import org.aibles.item_service.entity.CategoryItem;
import org.aibles.item_service.exception.CategoryIdNotFoundException;
import org.aibles.item_service.exception.ItemIdNotFoundException;
import org.aibles.item_service.repository.CategoryItemRepository;
import org.aibles.item_service.repository.CategoryRepository;
import org.aibles.item_service.repository.ItemRepository;
import org.aibles.item_service.service.CategoryItemService;

@Slf4j
public class CategoryItemServiceImpl implements CategoryItemService {

    public final CategoryItemRepository categoryItemRepository;
    public final CategoryRepository categoryRepository;
    public final ItemRepository itemRepository;
    public final UserClient userClient;

    public CategoryItemServiceImpl(CategoryItemRepository categoryItemRepository, CategoryRepository categoryRepository, ItemRepository itemRepository, UserClient userClient) {
        this.categoryItemRepository = categoryItemRepository;
        this.categoryRepository = categoryRepository;
        this.itemRepository = itemRepository;
        this.userClient = userClient;
    }

    @Override
    public CategoryItemResponse create(String categoryId, String itemId , String userId) {
        checkCategoryId(categoryId);
        checkItemId(itemId);
        userClient.getUserDetail(userId);
        return CategoryItemResponse.from(categoryItemRepository.save(CategoryItem.of(itemId, categoryId)));
    }

    @Override
    public void checkCategoryId(String categoryId) {
        log.info("(checkCategoryId)categoryId: {}", categoryId);
        if(!categoryRepository.existsById(categoryId)) {
            log.error("(checkCategoryId)categoryId: {}", categoryId);
            throw new CategoryIdNotFoundException(categoryId);
        }
    }

    @Override
    public void checkItemId(String itemId) {
        log.info("(checkItemId)itemId: {}", itemId);
        if(!itemRepository.existsById(itemId)) {
            log.error("(checkItemId)itemId: {}", itemId);
            throw new ItemIdNotFoundException(itemId);
        }
    }

    @Override
    @Transactional
    public List<String> findItemIdByCategoryId(String categoryId) {
        log.info("(findItemIdByCategoryId)categoryId: {}", categoryId);
        checkCategoryId(categoryId);
        return categoryItemRepository.findItemIdByCategoryId(categoryId);
    }
}
