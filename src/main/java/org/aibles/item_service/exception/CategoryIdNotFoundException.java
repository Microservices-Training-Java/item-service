package org.aibles.item_service.exception;

import org.trainingjava.core_exception.NotFoundException;

public class CategoryIdNotFoundException extends NotFoundException {

    public CategoryIdNotFoundException(String categoryId){
        setStatus(404);
        setCode("org.aibles.item_service.exception.CategoryIdNotFoundException");
        addParams("Category not found", categoryId);

    }
}
