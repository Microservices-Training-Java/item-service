package org.aibles.item_service.dto.response;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.aibles.item_service.entity.ItemField;

@Data
@AllArgsConstructor
public class ItemFieldPaginationResponse {

  private List<ItemField> fieldList;
  private int pageNumber;
  private int pageSize;
  private int totalPages;

}
