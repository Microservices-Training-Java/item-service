package org.aibles.item_service.service;

import org.aibles.item_service.dto.request.WriteReviewCreateRequest;
import org.aibles.item_service.dto.response.WriteReviewResponse;

public interface ReviewService {

  /**
   * function to write review for item
   * @param customerId - id of the reviewer
   * @param itemId - id of item
   * @param request - Review information written for the item
   * @return Review information
   */
  WriteReviewResponse writeReview(String customerId, String itemId, WriteReviewCreateRequest request);

//  void validateReviewId(String reviewId);
//  void validateItemId(String itemId);

  void deleteReview(String customerId, String reviewId, String itemId);

}
