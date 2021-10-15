package com.getir.readingisgood.api.core.payload.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequest {

    private String username;
    private String bookId;
    private Integer purchaseAmount;

}