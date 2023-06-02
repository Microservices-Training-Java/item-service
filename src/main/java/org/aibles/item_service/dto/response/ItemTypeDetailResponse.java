package org.aibles.item_service.dto.response;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.aibles.item_service.entity.ItemType;

@AllArgsConstructor(staticName = "of")
@Data
@NoArgsConstructor
public class ItemTypeDetailResponse extends ItemTypeResponse{

  private List<String> listField;

  public static ItemTypeDetailResponse from(ItemTypeResponse itemType, List<String> listField) {
    var response = new ItemTypeDetailResponse();
    response.setId(itemType.getId());
    response.setType(itemType.getType());
    response.setListField(listField);
    return response;
  }


}
