package org.aibles.item_service.exception;

import org.trainingjava.core_exception.NotFoundException;

public class ReviewIdNotFoundException extends NotFoundException {
    public ReviewIdNotFoundException(String reviewId){
        super();
        setStatus(404);
        setCode("org.aibles.item_service.exception.ReviewIdNotFoundException");
    }
}
