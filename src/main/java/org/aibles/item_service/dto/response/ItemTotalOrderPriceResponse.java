package org.aibles.item_service.dto.response;


public class ItemTotalOrderPriceResponse {

  private Double totalAmount;

  private String orderId;

  public ItemTotalOrderPriceResponse() {
  }

  public ItemTotalOrderPriceResponse(Double totalAmount, String orderId) {
    this.totalAmount = totalAmount;
    this.orderId = orderId;
  }

  public Double getTotalAmount() {
    return totalAmount;
  }

  public void setTotalAmount(Double totalAmount) {
    this.totalAmount = totalAmount;
  }

  public String getOrderId() {
    return orderId;
  }

  public void setOrderId(String orderId) {
    this.orderId = orderId;
  }
}

