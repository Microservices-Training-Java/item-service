package org.aibles.item_service.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.aibles.item_service.entity.ItemField;

@AllArgsConstructor(staticName = "of")
@Data
@NoArgsConstructor
public class ItemFieldResponse {

  private String id;
  private String name;
  private String uniqueName;

  public static ItemFieldResponse from(ItemField itemField) {
    return ItemFieldResponse.of(
        itemField.getId(),
        itemField.getName(),
        itemField.getUniqueName()
    );
  }
}
