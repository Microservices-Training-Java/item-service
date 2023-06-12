package org.aibles.item_service.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ItemFieldValueDto {

  private String fieldId;
  private String value;
}
