package org.aibles.item_service.client.service;

import org.aibles.item_service.client.dto.response.OrderDetailByIdResponse;
import org.aibles.item_service.client.dto.response.OrderItemDetailResponse;

public interface OrderClient {

    OrderDetailByIdResponse getOrderDetail(String id);

    /**
     * function calls check if the item has been purchased by the user or not
     * @param itemId - itemId of order item detail
     * @param orderId -orderId of order item detail
     * @return - true when the product is available
     */
    boolean checkCustomerIdAndItemId(String itemId, String orderId);
}
