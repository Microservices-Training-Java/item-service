package org.aibles.item_service.client.service;

import org.aibles.item_service.client.dto.response.OrderDetailByIdResponse;

public interface OrderClient {

    OrderDetailByIdResponse getOrderDetail(String id);
}
