package org.aibles.item_service.exception;

import org.trainingjava.core_exception.NotFoundException;

public class ItemHasNoReviewsException extends NotFoundException {
    public ItemHasNoReviewsException(String itemId){
        super();
        setStatus(404);
        setCode("org.aibles.item_service.exception.ItemHasNoReviewsException");
        addParams("itemId", itemId);
    }
}
