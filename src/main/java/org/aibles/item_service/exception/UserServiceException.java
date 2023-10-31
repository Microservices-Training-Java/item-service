package org.aibles.item_service.exception;

public class UserServiceException extends BaseExceptionRequest{

  public UserServiceException() {
    setStatus(404);
    setCode("org.aibles.item_service.exception.UserServiceException");
  }
}
