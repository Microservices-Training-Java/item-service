package org.aibles.item_service.repository;

import java.util.List;
import org.aibles.item_service.dto.response.ItemResponse;
import org.aibles.item_service.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<Item, String> {

  boolean existsByItemTypeId(String itemTypeId);

  void deleteAllByItemTypeId(String itemtypeId);

  List<Item> findAllByItemTypeId(String itemtypeId);
}
