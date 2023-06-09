package org.aibles.item_service.dto.response;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.aibles.item_service.entity.ItemType;

@Data
@AllArgsConstructor
public class ItemTypePaginationResponse {

  private List<ItemType> itemTypeList;
  private int currentPage;
  private int pageSize;
  private int totalPages;

}
