package org.aibles.item_service.exception;

public class ValidateCustomerDeleteReviewException extends BadRequestException{
        public ValidateCustomerDeleteReviewException(){
        super();
        setStatus(404);
        setCode("org.aibles.item_service.exception.ValidateCustomerDeleteReviewException");
    }
}
