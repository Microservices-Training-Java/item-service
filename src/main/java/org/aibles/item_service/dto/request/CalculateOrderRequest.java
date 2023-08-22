package org.aibles.item_service.dto.request;

import javax.validation.constraints.NotBlank;

public class CalculateOrderRequest {

    @NotBlank
    private String orderId;

    public CalculateOrderRequest(String orderId) {
        this.orderId = orderId;
    }

    public CalculateOrderRequest() {
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }


}
