package org.aibles.item_service.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ItemFieldCreateRequest {

  String id;
  String name;
  String uniqueName;

}
