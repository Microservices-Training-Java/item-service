package org.aibles.item_service.dto.request;


import jakarta.validation.constraints.NotBlank;

public class ItemCalculateRequest {

  @NotBlank
  private String orderId;


  public ItemCalculateRequest() {
  }

  public String getOrderId() {
    return orderId;
  }

  public void setOrderId(String orderId) {
    this.orderId = orderId;
  }
}



