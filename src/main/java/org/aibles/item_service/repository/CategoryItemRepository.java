package org.aibles.item_service.repository;

import java.util.List;
import org.aibles.item_service.entity.CategoryItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CategoryItemRepository extends JpaRepository<CategoryItem, String> {

  @Query("select ci.itemId from CategoryItem ci where ci.categoryId = :categoryId")
  List<String> findItemIdByCategoryId(String categoryId);

  void deleteByCategoryId(String categoryId);
}
