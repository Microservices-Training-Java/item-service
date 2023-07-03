package org.aibles.item_service.dto.request;

import javax.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.aibles.item_service.validation.ValidateUUID;

@Data
@NoArgsConstructor
public class ItemIdRequest {

  @ValidateUUID
  @NotBlank
  String itemId;
}
