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
    var response = new ItemFieldResponse();
    response.setId(itemField.getId());
    response.setName(itemField.getName());
    response.setUniqueName(itemField.getUniqueName());
    return response;
  }
}
