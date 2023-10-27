package org.aibles.item_service.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.aibles.item_service.dto.request.CategoryItemCreateRequest;
import org.aibles.item_service.dto.response.CategoryItemResponse;
import org.aibles.item_service.entity.CategoryItem;
import org.aibles.item_service.exception.CategoryIdNotFoundException;
import org.aibles.item_service.exception.ItemIdNotFoundException;
import org.aibles.item_service.repository.CategoryItemRepository;
import org.aibles.item_service.repository.CategoryRepository;
import org.aibles.item_service.repository.ItemRepository;
import org.aibles.item_service.service.CategoryItemService;
import org.aibles.item_service.service.CategoryService;

@Slf4j
public class CategoryItemServiceImpl implements CategoryItemService {

    public final CategoryItemRepository categoryItemRepository;
    public final CategoryRepository categoryRepository;
    public final ItemRepository itemRepository;
    public final CategoryService categoryService;

    public CategoryItemServiceImpl(CategoryItemRepository categoryItemRepository, CategoryRepository categoryRepository, ItemRepository itemRepository, CategoryService categoryService) {
        this.categoryItemRepository = categoryItemRepository;
        this.categoryRepository = categoryRepository;
        this.itemRepository = itemRepository;
        this.categoryService = categoryService;
    }

    @Override
    public CategoryItemResponse create(CategoryItemCreateRequest request, String userId) {
        checkCategoryId(request.getCategoryId());
        checkItemId(request.getItemId());
        categoryService.getUserDetail(userId);
        return CategoryItemResponse.from(categoryItemRepository.save(CategoryItem.of(request.getItemId(), request.getCategoryId())));
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
}
