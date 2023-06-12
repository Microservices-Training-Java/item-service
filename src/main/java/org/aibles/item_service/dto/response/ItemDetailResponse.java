package org.aibles.item_service.dto.response;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.aibles.item_service.dto.ItemFieldValueDto;

@AllArgsConstructor(staticName = "of")
@Data
@NoArgsConstructor
public class ItemDetailResponse extends ItemResponse{

  List<ItemFieldValueDto> fieldValue;

  private String imageId;

  public static ItemDetailResponse from(ItemResponse item, List<ItemFieldValueDto> fieldValue, String imageId) {
    var response = new ItemDetailResponse();
    response.setId(item.getId());
    response.setItemTypeId(item.getItemTypeId());
    response.setFieldValue(fieldValue);
    response.setImageId(imageId);
    return response;
  }

}
