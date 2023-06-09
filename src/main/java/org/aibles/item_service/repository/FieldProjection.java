package org.aibles.item_service.repository;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FieldProjection {

  private String id;
  private String name;
  private String uniqueName;

}
