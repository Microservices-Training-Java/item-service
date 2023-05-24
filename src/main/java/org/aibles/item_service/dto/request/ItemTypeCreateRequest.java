package org.aibles.item_service.dto.request;

import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.aibles.item_service.entity.ItemField;

@Data
@NoArgsConstructor
public class ItemTypeCreateRequest {
  private String type;

  private List<String> listField;
}
