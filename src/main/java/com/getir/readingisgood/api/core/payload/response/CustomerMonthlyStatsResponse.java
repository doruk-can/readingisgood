package com.getir.readingisgood.api.core.payload.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerMonthlyStatsResponse {

    private String username;
    private String month;
    private int totalBookCount;
    private double totalPurchasedAmount;
    private int totalOrderCount;

}
