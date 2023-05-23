package org.aibles.item_service.entity;

import java.util.UUID;
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
@Table(name = "item_type")
public class ItemType {

  @Id
  private String id;
  private String type;
  @PrePersist
  private void prePersistId() {
    this.id = this.id == null ? UUID.randomUUID().toString() : this.id;
  }

  public static ItemType of(String type){
    ItemType itemType = new ItemType();
    itemType.setType(type);
    return itemType;
  }
}
