package org.aibles.item_service.dto.response;

public class CalculateOrderResponse {

    private Double totalAmount;

    private String orderId;

    public CalculateOrderResponse(Double totalAmount, String orderId) {
        this.totalAmount = totalAmount;
        this.orderId = orderId;
    }

    public CalculateOrderResponse() {
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

    @Override
    public String toString() {
        return "CalculateOrderResponse{" +
                "totalAmount=" + totalAmount +
                ", orderId='" + orderId + '\'' +
                '}';
    }
}
