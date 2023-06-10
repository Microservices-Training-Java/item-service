package org.aibles.item_service.dto.request;

import java.util.List;
import java.util.Map;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ItemCreateRequest {

  Map<String, String> value;

  String imageId;

}
