package org.aibles.item_service.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ItemFieldRequest {

  private String name;
  private String uniqueName;

}