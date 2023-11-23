package org.aibles.item_service.client.service;

public interface CustomerClient {

  /**
   * function calls customer detail
   * @param id - id of customer detail
   * @return customer detail information
   */
  String getCustomerDetail(String id);
}
