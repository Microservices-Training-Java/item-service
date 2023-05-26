package org.aibles.item_service.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.aibles.item_service.entity.Item;

@AllArgsConstructor(staticName = "of")
@Data
@NoArgsConstructor
public class ItemResponse {

  private String id;
  private String itemTypeId;

  public static ItemResponse from(Item item) {
    return ItemResponse.of(
        item.getId(),
        item.getItemTypeId()
    );
  }
}
