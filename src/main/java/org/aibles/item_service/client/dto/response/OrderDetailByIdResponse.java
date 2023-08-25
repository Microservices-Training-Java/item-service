package org.aibles.item_service.client.dto.response;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.aibles.item_service.client.dto.ItemDto;

@Data
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)

public class OrderDetailByIdResponse {
  private String id;
  private List<ItemDto> items;

  public static OrderDetailByIdResponse from(String id, List<ItemDto> items){
    OrderDetailByIdResponse orderDetailByIdResponse = new OrderDetailByIdResponse();
    orderDetailByIdResponse.setId(id);
    orderDetailByIdResponse.setItems(items);
    return orderDetailByIdResponse;
  }
}
