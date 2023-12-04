package org.aibles.item_service.service.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aibles.item_service.client.dto.response.OrderItemDetailResponse;
import org.aibles.item_service.client.service.CustomerClient;
import org.aibles.item_service.client.service.OrderClient;
import org.aibles.item_service.dto.request.WriteReviewCreateRequest;
import org.aibles.item_service.dto.response.WriteReviewResponse;
import org.aibles.item_service.entity.Review;
import org.aibles.item_service.exception.ItemHasNoReviewsException;
import org.aibles.item_service.exception.ItemIdNotFoundException;
import org.aibles.item_service.exception.ReviewIdNotFoundException;
import org.aibles.item_service.exception.ValidateCustomerDeleteReviewException;
import org.aibles.item_service.repository.ReviewRepository;
import org.aibles.item_service.service.ReviewService;
import org.trainingjava.core_exception.NotFoundException;

import java.util.Optional;

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
  public String deleteReview(String customerId, String reviewId, String itemId) {
    log.info("(deleteReview)customerId: {}, reviewId: {}, itemId: {}", customerId, reviewId, itemId);

    customerClient.getCustomerDetail(customerId);
    if(!repository.existsById(reviewId)){
      throw new ReviewIdNotFoundException(reviewId);
    }

    if(!repository.existsByCustomerId(customerId)){
      throw new ValidateCustomerDeleteReviewException();
    }

    if(!repository.existsByItemId(itemId)){
      throw new ItemHasNoReviewsException(itemId);
    }

    if(!repository.existsByIdAndCustomerIdAndItemId(customerId, reviewId, itemId)){
      throw new ValidateCustomerDeleteReviewException();
    }
    repository.deleteById(reviewId);
    return "Delete review successfully";
  }
}
