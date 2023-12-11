package org.aibles.item_service.repository;

import org.aibles.item_service.entity.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Repository
public interface ItemRepository extends JpaRepository<Item, String> {

  boolean existsByItemTypeId(String itemTypeId);

  void deleteAllByItemTypeId(String itemtypeId);

  List<Item> findAllByItemTypeId(String itemtypeId);

  @Query("Select new org.aibles.item_service.repository.ValueProjection("
      + "  item_field.name, "
      + "  item_field_value.value)"
      + "  from ItemFieldValue item_field_value"
      + "  join ItemField item_field on item_field.id = item_field_value.fieldId"
      + "  where item_field_value.itemId = :id")
  List<ValueProjection> findItemDetailByItemId(String id);

  @Query("select i.id " +
          "from Item i " +
          "left join ItemFieldValue ifv on ifv.itemId = i.id " +
          "left join ItemField if2 on if2.id = ifv.fieldId and if2.name = 'name' " +
          "where if2.name = 'name' and ifv.value like %:name%")
  Page<String> getItemIdByName(String name, Pageable pageable);

  @Query("SELECT i.id " +
          "FROM ItemFieldValue ifv " +
          "JOIN ItemField if2  ON if2.id = ifv.fieldId " +
          "JOIN Item i ON i.id = ifv.itemId GROUP BY i.id ORDER BY i.id ASC")
  Page<String> getAllItemId(Pageable pageable);
}