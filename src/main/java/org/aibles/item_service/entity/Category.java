package org.aibles.item_service.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.aibles.item_service.entity.base.BaseEntity;

@Data
@Entity
@NoArgsConstructor
@Table(name = "categories")
public class Category extends BaseEntity {

  private String categoryName;
  private String parentId;
  private String description;

  public static Category of(String categoryName, String parentId, String description) {
    Category category = new Category();
    category.setCategoryName(categoryName);
    category.setParentId(parentId);
    category.setDescription(description);
    return category;
  }

}
