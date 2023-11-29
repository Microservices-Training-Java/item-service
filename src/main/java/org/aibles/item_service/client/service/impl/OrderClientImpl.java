package org.aibles.item_service.client.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.aibles.item_service.client.dto.response.OrderDetailByIdResponse;
import org.aibles.item_service.client.dto.response.OrderItemDetailResponse;
import org.aibles.item_service.client.service.OrderClient;
import org.aibles.item_service.dto.response.Response;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

@Slf4j
public class OrderClientImpl implements OrderClient {

    private static final String ORDER_DETAIL_API = "http://order-service/api/v1/orders/%s";
    @LoadBalanced
    private final RestTemplate restTemplate;

    private final ObjectMapper objectMapper;

    public OrderClientImpl(RestTemplate restTemplate, ObjectMapper objectMapper) {
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;
    }

    @Override
    public OrderDetailByIdResponse getOrderDetail(String id) {
        log.info("(getOrderDetail)id : {}", id);
        Response response = restTemplate.getForObject(String.format(ORDER_DETAIL_API, id), Response.class);
        return objectMapper.convertValue(response.getData(), OrderDetailByIdResponse.class);
    }

    @Override
    public OrderItemDetailResponse getOrderItemDetail(String itemId, String customerId) {
        log.info("(getOrderItemDetail)itemId: {}, customerId: {}", itemId, customerId);
        String ORDER_ITEM_DETAIL_API = "/items/" + itemId + "/customers/" + customerId;
        Response response = restTemplate.getForObject(String.format(ORDER_DETAIL_API, ORDER_ITEM_DETAIL_API), Response.class);
        return objectMapper.convertValue(response.getData(), OrderItemDetailResponse.class);
    }
}
