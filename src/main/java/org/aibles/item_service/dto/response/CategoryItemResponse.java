package org.aibles.item_service.dto.response;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.aibles.item_service.entity.CategoryItem;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@Data
@NoArgsConstructor
public class CategoryItemResponse {

    private String id;
    private String categoryId;
    private String itemId;

    public static CategoryItemResponse from(CategoryItem categoryItem) {
        CategoryItemResponse response = new CategoryItemResponse();
        response.setId(categoryItem.getId());
        response.setCategoryId(categoryItem.getCategoryId());
        response.setItemId(categoryItem.getItemId());
        return response;
    }

}
