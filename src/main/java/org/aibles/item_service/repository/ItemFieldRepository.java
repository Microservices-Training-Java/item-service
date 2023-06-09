package org.aibles.item_service.repository;

import java.util.List;
import org.aibles.item_service.entity.ItemField;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemFieldRepository extends JpaRepository<ItemField, String> {

  @Query("select it.name from ItemField it  where it.id = :id")
  String findNameById(String id);

  boolean existsByName(String name);

  boolean existsByUniqueName(String uniqueName);

  @Query("SELECT new org.aibles.item_service.repository.FieldProjection("
      +"f.id, "
      +"f.name,"
      +"f.uniqueName)"
      + "FROM ItemType it "
      + "JOIN ItemTypeField tf on it.id = tf.itemTypeId "
      + "JOIN ItemField f on tf.fieldId = f.id WHERE it.id = :itemTypeId ")
  List<FieldProjection> findALLByItemTypeId(String itemTypeId);
}
