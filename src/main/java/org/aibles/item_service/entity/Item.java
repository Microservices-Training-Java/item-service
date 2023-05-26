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
@Table(name = "item")
public class Item {

  @Id
  private String id;
  @Column(name = "item_type_id")
  private String itemTypeId;
  @PrePersist
  private void prePersistId() {
    this.id = this.id == null ? UUID.randomUUID().toString() : this.id;
  }

  public static Item of(String itemTypeId){
    Item item = new Item();
    item.setItemTypeId(itemTypeId);
    return item;
  }
}
