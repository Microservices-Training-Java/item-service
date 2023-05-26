package org.aibles.item_service.repository;

import java.util.List;
import org.aibles.item_service.entity.ItemFieldValue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemFieldValueRepository extends JpaRepository<ItemFieldValue, String> {

  List<ItemFieldValue> findAllByItemId(String itemId);
}
