package org.aibles.item_service.dto.response;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.aibles.item_service.dto.ItemFieldValueDto;
import org.aibles.item_service.repository.ItemProjection;
import org.aibles.item_service.repository.ValueProjection;

@AllArgsConstructor(staticName = "of")
@Data
@NoArgsConstructor
public class DetailResponse {

  private ItemProjection itemProjection;
  private List<ValueProjection> listFieldNameAndValue;

  public static DetailResponse from(ItemProjection itemProjection, List<ValueProjection> listValue) {
    var response = new DetailResponse();
    response.setItemProjection(itemProjection);
    response.setListFieldNameAndValue(listValue);
    return response;
  }
}
