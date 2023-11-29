package org.aibles.item_service.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.aibles.item_service.entity.base.BaseEntity;

@AllArgsConstructor(staticName = "of")
@Data
@Entity
@NoArgsConstructor
@Table(name = "review")
public class Review extends BaseEntity {

  private String itemId;
  private String customerId;
  private Integer rating;
  private String reviewText;
}
