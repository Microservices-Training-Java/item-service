package org.aibles.item_service.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ItemFieldValueDto {

  @NotNull(message = "Field Id cannot be null")
  @Pattern(regexp = "[a-fA-F0-9]{8}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{12}", message = "Invalid Field Id")
  private String fieldId;
  @NotNull(message = "Value cannot be null")
  private String value;
}
