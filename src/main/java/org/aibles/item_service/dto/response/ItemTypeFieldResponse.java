package org.aibles.item_service.dto.response;

import javax.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.aibles.item_service.entity.ItemTypeField;

@AllArgsConstructor(staticName = "of")
@Data
@NoArgsConstructor
public class ItemTypeFieldResponse {

  private String id;
  private String itemId;
  private String fieldId;

  public static ItemTypeFieldResponse from(ItemTypeField itemTypeField) {
    return ItemTypeFieldResponse.of(
        itemTypeField.getId(),
        itemTypeField.getItemTypeId(),
        itemTypeField.getFieldId()
    );
  }
}
