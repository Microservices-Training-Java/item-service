package org.aibles.item_service.client.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aibles.item_service.client.dto.response.OrderDetailByIdResponse;
import org.aibles.item_service.client.service.UserClient;
import org.aibles.item_service.dto.response.Response;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

@Slf4j
@RequiredArgsConstructor
public class UserClientImpl implements UserClient {

  private static final String USER_DETAIL_API = "http://user-service/api/v1/users";
  @LoadBalanced
  private final RestTemplate restTemplate;

  @Override
  public String getUserDetail(String userId) {
    log.info("(getUserDetail)userId : {}", userId);
    String USER_GET_DETAIL_API = USER_DETAIL_API + '/' + userId;
    ResponseEntity<String> response =
        restTemplate.exchange(USER_GET_DETAIL_API, HttpMethod.GET, null, String.class);
    return response.getBody().toString();
  }
}
