package com.getir.readingisgood.api.core.model.service;

import com.getir.readingisgood.api.core.exception.OrderNotFoundException;
import com.getir.readingisgood.api.core.model.domain.Order;
import com.getir.readingisgood.api.core.model.repository.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
public class StatisticsServiceImpl {

    @Autowired
    private  OrderRepository orderRepository;

    @Autowired
    private OrderServiceImpl orderService;


    public Map<String, List<Order>> getCustomerOrderSummaryPerMonth(String username) {

        Map<String, List<Order>> orderSummaryMap = null;
        try {
            orderSummaryMap = orderService.getOrdersByUsername(username)
                    .stream()
                    .collect(Collectors.groupingBy(x -> x.getOrderCreationDate().getMonth().toString()));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return orderSummaryMap;
    }


}
