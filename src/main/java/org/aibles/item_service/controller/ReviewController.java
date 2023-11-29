package org.aibles.item_service.controller;

import static org.aibles.item_service.constant.ItemApiConstant.BaseUrl.REVIEW_BASE_URL;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aibles.item_service.dto.request.WriteReviewCreateRequest;
import org.aibles.item_service.dto.response.Response;
import org.aibles.item_service.service.ReviewService;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(REVIEW_BASE_URL)
@Slf4j
@AllArgsConstructor
public class ReviewController {

  private final ReviewService service;

  @PostMapping("/{item_id}/review")
  @ResponseStatus(HttpStatus.OK)
  public Response writeReview(@Valid @RequestHeader(value = "customer_id") String customerId,
      @Validated @PathVariable("item_id") String itemId,
      @Valid @RequestBody WriteReviewCreateRequest request  ) {
    log.info("(writeReview)customerId: {}, itemId: {}, request : {}", customerId, itemId, request);
    return Response.of(
        HttpStatus.CREATED.value(), service.writeReview(customerId, itemId, request));
  }
}
