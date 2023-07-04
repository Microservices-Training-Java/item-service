package org.aibles.item_service.dto.response;

import java.util.List;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.aibles.item_service.repository.ValueProjection;

@AllArgsConstructor(staticName = "of")
@Data
@NoArgsConstructor
public class DetailResponse {

  private Map<String, List<ValueProjection>> items;

  public static DetailResponse from(Map<String, List<ValueProjection>> item) {
    var response = new DetailResponse();
    response.setItems(item);
    return response;
  }
}
