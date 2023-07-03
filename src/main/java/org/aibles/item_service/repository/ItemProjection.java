package org.aibles.item_service.repository;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemProjection {

  private String id;
  private String type;
  private String fieldName;
  private String value;
}
