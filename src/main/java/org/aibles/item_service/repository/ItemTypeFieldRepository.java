package org.aibles.item_service.repository;

import org.aibles.item_service.entity.ItemTypeField;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemTypeFieldRepository extends JpaRepository<ItemTypeField, String> {

}
