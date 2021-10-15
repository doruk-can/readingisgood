package com.getir.readingisgood.api.v1;

import com.getir.readingisgood.api.core.model.domain.EOrderStatus;
import com.getir.readingisgood.api.core.model.domain.Order;
import com.getir.readingisgood.api.core.model.service.StatisticsServiceImpl;
import com.getir.readingisgood.api.core.payload.response.CustomerMonthlyStatsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1/statistics")
public class StatisticsController {

    @Autowired
    private StatisticsServiceImpl statisticsService;

    @GetMapping("/monthly")
    public List<CustomerMonthlyStatsResponse> getMonthlyCustomerStats(@RequestParam String username) {

        Map<String, List<Order>> myMap = statisticsService.getCustomerOrderSummaryPerMonth(username);

        List<CustomerMonthlyStatsResponse> customerMonthlyStatsList = new ArrayList<>();
        CustomerMonthlyStatsResponse temp = new CustomerMonthlyStatsResponse();
        temp.setUsername(username);

        int totalBookCount;
        double totalPurchasedAmount;
        int totalOrderCount;

        for (Map.Entry<String,List<Order>> entry : myMap.entrySet()) {
            temp.setMonth(entry.getKey());
            totalBookCount = 0;
            totalPurchasedAmount = 0;
            totalOrderCount = 0;
            for(Order order : entry.getValue()) {
                if(order.getOrderStatus().equals(EOrderStatus.APPROVED)) {
                    totalOrderCount += 1;
                    totalPurchasedAmount += order.getTotalPrice();
                    totalBookCount += order.getPurchaseAmount();
                }
            }
            temp.setTotalBookCount(totalBookCount);
            temp.setTotalPurchasedAmount(totalPurchasedAmount);
            temp.setTotalOrderCount(totalOrderCount);

            customerMonthlyStatsList.add(temp);
        }

       return customerMonthlyStatsList;
    }


}
