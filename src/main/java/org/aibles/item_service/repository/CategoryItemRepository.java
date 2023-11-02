package org.aibles.item_service.repository;

import java.util.List;
import org.aibles.item_service.entity.CategoryItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface CategoryItemRepository extends JpaRepository<CategoryItem, String> {

  @Query("select ci.itemId from CategoryItem ci where ci.categoryId = :categoryId")
  List<String> findItemIdByCategoryId(String categoryId);

  @Transactional
  @Modifying
  @Query("DELETE FROM CategoryItem ci WHERE ci.categoryId = :categoryId")
  void deleteCategoryAndItems(String categoryId);
}
