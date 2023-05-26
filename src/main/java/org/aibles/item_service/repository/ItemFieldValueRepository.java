package org.aibles.item_service.repository;

import org.aibles.item_service.entity.ItemFieldValue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemFieldValueRepository extends JpaRepository<ItemFieldValue, String> {

  boolean existsByItemId(String itemId);

  void deleteAllByItemId(String itemId);
}
