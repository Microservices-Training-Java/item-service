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
@Table(name = "item_field")
public class ItemField {

  @Id
  private String id;
  private String name;
  @Column(name = "unique_name")
  private String uniqueName;

  @PrePersist
  private void prePersistId() {
    this.id = this.id == null ? UUID.randomUUID().toString() : this.id;
  }

  public static ItemField of(String name, String uniqueName) {
    ItemField itemField = new ItemField();
    itemField.setName(name);
    itemField.setUniqueName(uniqueName);
    return itemField;
  }
}
