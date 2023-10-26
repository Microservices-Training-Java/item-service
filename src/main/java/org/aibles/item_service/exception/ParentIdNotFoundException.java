package org.aibles.item_service.exception;

public class ParentIdNotFoundException extends BaseExceptionRequest {

  public ParentIdNotFoundException(String parentId) {
    setStatus(404);
    setCode("org.aibles.item_service.exception.ParentIdNotFoundException");
    addParams("Parent_id not found", parentId);
  }
}
