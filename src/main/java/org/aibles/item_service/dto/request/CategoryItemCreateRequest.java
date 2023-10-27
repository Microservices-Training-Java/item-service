package org.aibles.item_service.dto.request;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@Data
@NoArgsConstructor
public class CategoryItemCreateRequest {

    @NotBlank(message = "Item id can’t be null")
    private String itemId;
    @NotBlank(message = "Category id can’t be null")
    private String categoryId;
}
