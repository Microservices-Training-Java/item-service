package org.aibles.item_service.dto;

import javax.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.aibles.item_service.validation.ValidateUUID;

@Data
@NoArgsConstructor
public class ItemFieldValueDto {

  @ValidateUUID
  @NotBlank
  private String fieldId;
  @NotBlank
  private String value;
}
