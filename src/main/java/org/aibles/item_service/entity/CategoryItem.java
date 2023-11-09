package org.aibles.item_service.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.aibles.item_service.entity.base.BaseEntity;

@Data
@Entity
@NoArgsConstructor
@Table(name = "category_item")
public class CategoryItem extends BaseEntity {

    private String itemId;
    private String categoryId;

    public static CategoryItem of(String itemId, String categoryId) {
        CategoryItem categoryItem = new CategoryItem();
        categoryItem.setCategoryId(categoryId);
        categoryItem.setItemId(itemId);
        return categoryItem;
    }

}
