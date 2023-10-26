package org.aibles.item_service.exception;

public class CategoryNameAlreadyExitException extends ConflictException {

  public CategoryNameAlreadyExitException(String categoryName) {
    setCode("org.aibles.item_service.exception.CategoryNameAlreadyExitException");
    addParams("Category name already exists", categoryName);
  }
}
