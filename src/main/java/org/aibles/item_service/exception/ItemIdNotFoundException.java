package org.aibles.item_service.exception;

import org.trainingjava.core_exception.NotFoundException;

public class ItemIdNotFoundException extends NotFoundException {

    public ItemIdNotFoundException(String itemId){
        setStatus(404);
        setCode("org.aibles.item_service.exception.ItemIdNotFoundException");
        addParams("Item id not found", itemId);
    }
}
