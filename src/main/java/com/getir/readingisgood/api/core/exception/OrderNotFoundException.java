package com.getir.readingisgood.api.core.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NO_CONTENT)
public class OrderNotFoundException extends RuntimeException{
    public OrderNotFoundException(String errorMessage) { super(errorMessage);}
}