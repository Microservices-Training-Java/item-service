package org.aibles.item_service.dto.response;

import java.util.List;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.aibles.item_service.entity.Item;

@AllArgsConstructor(staticName = "of")
@Data
@NoArgsConstructor
public class ItemDetailResponse extends ItemResponse{

  Map<String, String> fieldValue;

  private String imageId;

  public static ItemDetailResponse from(ItemResponse item, Map<String, String> fieldValue, String imageId) {
    var response = new ItemDetailResponse();
    response.setId(item.getId());
    response.setItemTypeId(item.getItemTypeId());
    response.setFieldValue(fieldValue);
    response.setImageId(imageId);
    return response;
  }

}
