package org.aibles.item_service.dto.request;

import java.util.List;
import java.util.Set;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ItemIdsRequest {

  private Set<String> itemIds;
}
