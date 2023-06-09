package org.aibles.item_service.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.aibles.item_service.entity.ItemFieldValue;

@AllArgsConstructor(staticName = "of")
@Data
@NoArgsConstructor
public class ItemFieldValueResponse {

  private String id;
  private String itemId;
  private String fieldId;
  private String value;
  private String imageId;

  public static ItemFieldValueResponse from(ItemFieldValue itemFieldValue) {
    return ItemFieldValueResponse.of(
        itemFieldValue.getId(),
        itemFieldValue.getItemId(),
        itemFieldValue.getFieldId(),
        itemFieldValue.getValue(),
        itemFieldValue.getImageId()
    );
  }
}
