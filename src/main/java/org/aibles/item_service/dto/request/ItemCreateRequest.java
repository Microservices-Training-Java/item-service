package org.aibles.item_service.dto.request;

import java.util.List;
import javax.validation.Valid;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.aibles.item_service.dto.ItemFieldValueDto;

@Data
@NoArgsConstructor
public class ItemCreateRequest {

  @Valid
  private List<ItemFieldValueDto> fieldValues;

}
