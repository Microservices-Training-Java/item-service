package org.aibles.item_service.client.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aibles.item_service.client.service.CustomerClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

@Slf4j
@RequiredArgsConstructor
public class CustomerClientImpl implements CustomerClient {

  private static final String CUSTOMER_DETAIL_API = "http://customer-service/api/v1/customers";
  @LoadBalanced
  private final RestTemplate restTemplate;

  @Override
  public String getCustomerDetail(String id) {
    log.info("(getCustomerDetail)customerId : {}", id);
    String CUSTOMER_GET_DETAIL_API = CUSTOMER_DETAIL_API + '/' + id;
    ResponseEntity<String> response =
        restTemplate.exchange(CUSTOMER_GET_DETAIL_API, HttpMethod.GET, null, String.class);
    return response.getBody().toString();
  }
}
