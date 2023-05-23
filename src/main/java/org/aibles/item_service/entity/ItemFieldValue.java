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
@Table(name = "item_field_value")
public class ItemFieldValue {

  @Id
  private String id;
  @Column(name = "item_id")
  private String itemId;
  @Column(name = "field_id")
  private String fieldId;
  @PrePersist
  private void prePersistId() {
    this.id = this.id == null ? UUID.randomUUID().toString() : this.id;
  }
}
