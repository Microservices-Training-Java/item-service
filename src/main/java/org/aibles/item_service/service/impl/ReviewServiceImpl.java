package org.aibles.item_service.service.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.var;
import org.aibles.item_service.client.dto.response.OrderItemDetailResponse;
import org.aibles.item_service.client.service.CustomerClient;
import org.aibles.item_service.client.service.OrderClient;
import org.aibles.item_service.dto.request.WriteReviewCreateRequest;
import org.aibles.item_service.dto.response.WriteReviewResponse;
import org.aibles.item_service.entity.Review;
import org.aibles.item_service.repository.ReviewRepository;
import org.aibles.item_service.service.ReviewService;
import org.trainingjava.core_exception.NotFoundException;

@AllArgsConstructor
@Slf4j
public class ReviewServiceImpl implements ReviewService {

  private final ReviewRepository repository;
  private final OrderClient orderClient;
  private final CustomerClient customerClient;

  @Override
  public WriteReviewResponse writeReview(String customerId, String itemId,
      WriteReviewCreateRequest request) {
    log.info("(writeReview)customerId: {}, itemId: {}, request : {}", customerId, itemId, request);
    customerClient.getCustomerDetail(customerId);
    orderClient.getOrderItemDetail(itemId, customerId);
    return WriteReviewResponse.from(repository.save(Review.of(itemId, customerId, request.getRate(),
        request.getReview())));
  }

  @Override
  public void validateReviewId(String reviewId) {
    log.info("(validateReviewId)reviewId: {}", reviewId);
    var review =
            repository
                    .findById(reviewId)
                    .orElseThrow(() -> new NotFoundException(reviewId, Review.class.getSimpleName()));
  }

  @Override
  public void validateItemId(String itemId) {
    log.info("(validateItemId)itemId: {}", itemId);
    Optional<Review> review =
            repository
                    .findReviewByItemId(itemId)
                    .orElseThrow(() -> new NotFoundException(itemId, Review.class.getSimpleName()));
  }
}
