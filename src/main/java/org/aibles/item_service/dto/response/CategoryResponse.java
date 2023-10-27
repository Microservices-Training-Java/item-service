package org.aibles.item_service.dto.response;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.aibles.item_service.entity.Category;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@Data
@NoArgsConstructor
public class CategoryResponse {

  private String id;
  private String categoryName;
  private String parentId;
  private String description;

  public static CategoryResponse from(Category category) {
    CategoryResponse response = new CategoryResponse();
    response.setId(category.getId());
    response.setCategoryName(category.getCategoryName());
    response.setParentId(category.getParentId());
    response.setDescription(category.getDescription());
    return response;
  }

}
