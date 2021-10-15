package com.getir.readingisgood.api.core.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class BookAlreadyExistException extends RuntimeException{
    public BookAlreadyExistException(String errorMessage) { super(errorMessage);}
}