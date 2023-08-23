package org.aibles.item_service.dto.response;

import jakarta.validation.constraints.NotBlank;

public class ItemOrder {

  @NotBlank
  private String orderId;

  private double totalAmount;

  public ItemOrder(String orderId, double totalAmount) {
    this.orderId = orderId;
    this.totalAmount = totalAmount;
  }

  public String getOrderId() {
    return orderId;
  }

  public void setOrderId(String orderId) {
    this.orderId = orderId;
  }

  public double getTotalAmount() {
    return totalAmount;
  }

  public void setTotalAmount(double totalAmount) {
    this.totalAmount = totalAmount;
  }
}
