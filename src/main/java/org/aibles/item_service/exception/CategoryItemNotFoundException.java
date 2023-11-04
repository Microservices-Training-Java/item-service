package org.aibles.item_service.exception;

import org.trainingjava.core_exception.NotFoundException;

public class CategoryItemNotFoundException extends NotFoundException {

    public CategoryItemNotFoundException(){
        setStatus(404);
        setCode("org.aibles.item_service.exception.CategoryItemNotFoundException");
    }
}
