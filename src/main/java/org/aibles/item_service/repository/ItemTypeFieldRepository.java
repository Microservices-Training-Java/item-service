package org.aibles.item_service.repository;

import java.util.List;
import org.aibles.item_service.entity.ItemTypeField;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemTypeFieldRepository extends JpaRepository<ItemTypeField, String> {

  List<ItemTypeField> findAllByItemTypeId(String itemTypeId);

  boolean existsByItemTypeIdAndFieldId(String itemTypeId, String fieldId);

  boolean existsByItemTypeId(String itemTypeId);

  void deleteAllByItemTypeId(String itemTypeId);


  List<String> findNameFieldByItemTypeId(String itemTypeId);

  @Query("SELECT itf.fieldId from ItemTypeField itf where itf.itemTypeId = :itemTypeId")
  List<String> findFieldIdByItemTypeId(String itemTypeId);

}
