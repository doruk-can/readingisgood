package com.getir.readingisgood.api.core.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidOrderAmountException extends RuntimeException{
    public InvalidOrderAmountException(String errorMessage) { super(errorMessage);}
}
