package org.aibles.item_service.repository;

import java.util.List;
import org.aibles.item_service.dto.response.ItemResponse;
import org.aibles.item_service.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<Item, String> {

  boolean existsByItemTypeId(String itemTypeId);

  void deleteAllByItemTypeId(String itemtypeId);

  List<Item> findAllByItemTypeId(String itemtypeId);

  //TODO: fix query get list by list itemId
  @Query("Select new org.aibles.item_service.repository.ItemProjection("
      +" item.id, "
      +" item_type.type,"
      +" item_field.name,"
      +" item_field_value.value)"
      +" from Item item JOIN ItemType item_type ON item.itemTypeId = item_type.id"
      +" JOIN ItemTypeField item_type_field ON item_type.id = item_type_field.itemTypeId"
      +" JOIN ItemField item_field ON item_type_field.fieldId = item_field.id"
      +" LEFT JOIN ItemFieldValue item_field_value ON item.id = item_field_value.itemId"
      +" AND item_field.id = item_field_value.fieldId"
      +" WHERE item.id = :id")
  ItemProjection findByItemId(String id);

}
