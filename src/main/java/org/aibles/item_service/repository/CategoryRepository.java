package org.aibles.item_service.repository;

import org.aibles.item_service.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, String> {

  Boolean existsByCategoryName(String categoryName);
}
