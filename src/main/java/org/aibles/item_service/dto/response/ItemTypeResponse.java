package org.aibles.item_service.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.aibles.item_service.entity.ItemType;

@AllArgsConstructor(staticName = "of")
@Data
@NoArgsConstructor
public class ItemTypeResponse {

  private String id;
  private String type;

  public static ItemTypeResponse from(ItemType itemType) {
    return ItemTypeResponse.of(
        itemType.getId(),
        itemType.getType()
    );
  }
}
