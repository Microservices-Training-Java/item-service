package org.aibles.item_service.dto.request;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@Data
@NoArgsConstructor
public class CategoryItemCreateRequest {

    @NotNull(message = "Item id can’t be null")
    private String itemId;
    @NotNull(message = "Category id can’t be null")
    private String categoryId;
}
