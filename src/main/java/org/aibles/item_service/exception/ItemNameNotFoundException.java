package org.aibles.item_service.exception;

import org.trainingjava.core_exception.NotFoundException;

public class ItemNameNotFoundException extends NotFoundException {
    public ItemNameNotFoundException(String itemName){
        super();
        setStatus(404);
        setCode("org.aibles.item_service.exception.ItemNameNotFoundException");
        // addParams("Item name not found", itemName);
        addParams("itemName", itemName);
    }

}
