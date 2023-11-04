package org.aibles.item_service.exception;

import org.trainingjava.core_exception.NotFoundException;

public class CategoryItemIdNotFoundException extends NotFoundException {

    public CategoryItemIdNotFoundException(String id){
        setStatus(404);
        setCode("org.aibles.item_service.exception.CategoryItemIdNotFoundException");
        addParams("CategoryItem not found", id);

    }
}
