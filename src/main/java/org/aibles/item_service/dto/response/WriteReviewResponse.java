package org.aibles.item_service.dto.response;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.aibles.item_service.entity.ItemTypeField;
import org.aibles.item_service.entity.Review;

@AllArgsConstructor(staticName = "of")
@Data
@NoArgsConstructor
public class WriteReviewResponse {

  private String itemId;
  private String reviewId;
  private String content;
  private LocalDateTime createdAt;

  public static WriteReviewResponse from(Review review) {
    return WriteReviewResponse.of(
        review.getItemId(),
        review.getId(),
        review.getReviewText(),
        LocalDateTime.ofInstant(Instant.ofEpochMilli(review.getCreatedAt()), ZoneId.systemDefault())
    );
  }
}
