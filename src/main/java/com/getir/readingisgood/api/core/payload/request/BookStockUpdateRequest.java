package com.getir.readingisgood.api.core.payload.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BookStockUpdateRequest {

    private String bookName;
    private Integer stockAmount;

}
