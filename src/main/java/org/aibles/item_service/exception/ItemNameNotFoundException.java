package org.aibles.item_service.exception;
import org.trainingjava.core_exception.NotFoundException;

public class ItemNameNotFoundException extends NotFoundException {
    public ItemNameNotFoundException(String itemName){
        setCode("org.aibles.item_service.exception.ItemNameNotFoundException");
        addParams("name", itemName);
    }

}
