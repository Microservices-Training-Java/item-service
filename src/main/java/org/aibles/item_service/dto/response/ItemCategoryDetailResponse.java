package org.aibles.item_service.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.aibles.item_service.entity.Category;

@AllArgsConstructor(staticName = "of")
@Data
@NoArgsConstructor
public class ItemCategoryDetailResponse {

  private String itemId;
  private String name;
  private String description;
  private double price;
  private String urlImage;
  private String categoryId;

  public static ItemCategoryDetailResponse from(
      String itemId,
      String name,
      String description,
      double price,
      String urlImage,
      String categoryId) {
    ItemCategoryDetailResponse response = new ItemCategoryDetailResponse();
    response.setItemId(itemId);
    response.setName(name);
    response.setDescription(description);
    response.setPrice(price);
    response.setUrlImage(urlImage);
    response.setCategoryId(categoryId);
    return response;
  }
}
