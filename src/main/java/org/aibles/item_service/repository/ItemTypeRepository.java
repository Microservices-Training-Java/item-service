package org.aibles.item_service.repository;

import org.aibles.item_service.entity.ItemType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemTypeRepository extends JpaRepository<ItemType, String> {

  boolean existsByType(String type);
}
