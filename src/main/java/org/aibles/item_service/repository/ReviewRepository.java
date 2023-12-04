package org.aibles.item_service.repository;

import org.aibles.item_service.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ReviewRepository extends JpaRepository<Review, String> {
    Optional<Review> findByIdAndCustomerIdAndItemId(String customerId, String reviewId, String itemId);

    boolean existsByItemId(String itemId);
    boolean existsByCustomerId(String customerId);
    boolean existsByIdAndCustomerIdAndItemId(String customerId, String reviewId, String itemId);
}
