package org.aibles.item_service.dto.request;

import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.aibles.item_service.dto.ItemFieldValueDto;

@Data
@NoArgsConstructor
public class ItemCreateRequest {

  private List<ItemFieldValueDto> fieldValues;

  String imageId;

}
