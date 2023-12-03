package org.aibles.item_service.dto.request;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@Data
@NoArgsConstructor
public class WriteReviewCreateRequest {

  @NotBlank(message = "Add your review for the product")
  @Size(min = 1, max = 100, message = "Length of review must be between 1-100")
  private String review;

  @Min(value = 1, message = "rate must be between 1-5")
  @Max(value = 5, message = "rate must be between 1-5")
  private Integer rate;
}
