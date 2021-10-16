package com.getir.readingisgood.api.core.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class BooksOutOfStockException extends Exception{
    public BooksOutOfStockException(String errorMessage) { super(errorMessage);}
}
