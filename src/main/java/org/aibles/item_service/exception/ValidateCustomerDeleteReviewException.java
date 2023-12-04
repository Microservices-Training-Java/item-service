package org.aibles.item_service.exception;

import org.trainingjava.core_exception.BadRequestException;

public class ValidateCustomerDeleteReviewException extends BadRequestException {
        public ValidateCustomerDeleteReviewException(){
        super();
        setStatus(404);
        setCode("org.aibles.item_service.exception.ValidateCustomerDeleteReviewException");
    }
}
