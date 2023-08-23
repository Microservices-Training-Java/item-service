package org.aibles.item_service.dto.response;


import jakarta.validation.constraints.NotBlank;


public class ItemTotalOrderPriceResponse {
  @NotBlank
  private Double totalAmount;

  private String orderId;

  public ItemTotalOrderPriceResponse(Double totalAmount, String orderId) {
    this.totalAmount = totalAmount;
    this.orderId = orderId;
  }

}

