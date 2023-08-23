package org.aibles.item_service.entity;

import java.util.UUID;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor(staticName = "of")
@Data
@Entity
@NoArgsConstructor
@Table(name = "item_field_value")
public class ItemFieldValue {

  @Id
  private String id;
  @Column(name = "item_id")
  private String itemId;
  @Column(name = "item_field_id")
  private String fieldId;
  private String value;
  @PrePersist
  private void prePersistId() {
    this.id = this.id == null ? UUID.randomUUID().toString() : this.id;
  }

  public static ItemFieldValue of(String itemId, String fieldId, String value){
    ItemFieldValue itemFieldValue = new ItemFieldValue();
    itemFieldValue.setItemId(itemId);
    itemFieldValue.setFieldId(fieldId);
    itemFieldValue.setValue(value);
    return itemFieldValue;
  }
}
