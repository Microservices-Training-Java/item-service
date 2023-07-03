package org.aibles.item_service.dto.response;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor(staticName = "of")
@Data
@NoArgsConstructor
public class ListItemDetailResponse {

  private List<ItemDetailResponse> listItem;

  public static ListItemDetailResponse from(List<ItemDetailResponse> listItem) {
    var response = new ListItemDetailResponse();
    response.setListItem(listItem);
    return response;
  }
}
