package org.aibles.item_service.client.service;

import org.aibles.item_service.client.dto.response.OrderDetailByIdResponse;
import org.aibles.item_service.client.dto.response.OrderItemDetailResponse;

public interface OrderClient {

    OrderDetailByIdResponse getOrderDetail(String id);

    /**
     * function calls order item detail
     * @param itemId - itemId of order item detail
     * @param orderId -orderId of order item detail
     * @return - order item detail information
     */
    OrderItemDetailResponse getOrderItemDetail(String itemId, String orderId);
}
