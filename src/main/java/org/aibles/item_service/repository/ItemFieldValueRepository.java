package org.aibles.item_service.repository;

import java.util.List;
import java.util.Optional;

import org.aibles.item_service.entity.ItemFieldValue;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemFieldValueRepository extends JpaRepository<ItemFieldValue, String> {

  List<ItemFieldValue> findAllByItemId(String itemId);
  boolean existsByItemId(String itemId);

  void deleteAllByItemId(String itemId);

  @Query("select ifv.value from ItemFieldValue ifv where ifv.itemId = :itemId " +
          "and ifv.fieldId = (select itf.id from ItemField itf where itf.name = 'price')")
  Optional<String> getPriceValueByItemId(String itemId);

  @Query("select ifv.value from ItemFieldValue ifv where ifv.itemId = :itemId " +
      "and ifv.fieldId = (select itf.id from ItemField itf where itf.name = :name)")
  String getValueItemByItemIdAndName(String itemId, String name);

}
