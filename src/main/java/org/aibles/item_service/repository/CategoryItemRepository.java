package org.aibles.item_service.repository;

import org.aibles.item_service.entity.CategoryItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryItemRepository extends JpaRepository<CategoryItem, String> {

    void deleteAllByCategoryIdAndItemId(String categoryId, String itemId);

    boolean existsByCategoryIdAndItemId(String categoryId, String itemId);
}
