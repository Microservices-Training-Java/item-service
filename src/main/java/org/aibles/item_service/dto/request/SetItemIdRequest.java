package org.aibles.item_service.dto.request;

import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SetItemIdRequest {

  private List<String> itemIds;
}
