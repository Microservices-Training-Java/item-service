package org.aibles.item_service.repository;

import org.aibles.item_service.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends JpaRepository<Image, String> {

    @Query("SELECT i.path FROM Image i WHERE i.id = :id")
    String getImagePathById(@Param("id") String id);
}
