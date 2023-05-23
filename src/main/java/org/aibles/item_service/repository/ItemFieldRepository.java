package org.aibles.item_service.repository;

import org.aibles.item_service.entity.ItemField;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemFieldRepository extends JpaRepository<ItemField, String> {

}
