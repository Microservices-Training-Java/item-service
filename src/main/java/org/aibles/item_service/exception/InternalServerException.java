package org.aibles.item_service.exception;

import org.springframework.http.HttpStatus;

public class InternalServerException extends BaseExceptionRequest {

    public InternalServerException() {
        setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        setCode("org.aibles.item_service.exception.InternalServerException");
    }
}
