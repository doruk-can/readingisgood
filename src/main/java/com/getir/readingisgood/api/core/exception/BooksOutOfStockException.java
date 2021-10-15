package com.getir.readingisgood.api.core.exception;

public class BooksOutOfStockException extends Exception{
    public BooksOutOfStockException(String errorMessage) { super(errorMessage);}
}
