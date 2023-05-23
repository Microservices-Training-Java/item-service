package org.aibles.item_service.entity;

import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor(staticName = "of")
@Data
@Entity
@NoArgsConstructor
@Table(name = "item_type_field")
public class ItemTypeField {

  @Id
  private String id;
  @Column(name = "item_type_id")
  private String itemTypeId;
  @Column(name = "item_field_id")
  private String fieldId;
  @PrePersist
  private void prePersistId() {
    this.id = this.id == null ? UUID.randomUUID().toString() : this.id;
  }

  public static ItemTypeField of(String itemTypeId, String fieldId) {
    ItemTypeField itemTypeField = new ItemTypeField();
    itemTypeField.setItemTypeId(itemTypeId);
    itemTypeField.setFieldId(fieldId);
    return itemTypeField;
  }
}
