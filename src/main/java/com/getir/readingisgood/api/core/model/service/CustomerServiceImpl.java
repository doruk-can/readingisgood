package com.getir.readingisgood.api.core.model.service;

import com.getir.readingisgood.api.core.exception.OrderNotFoundException;
import com.getir.readingisgood.api.core.model.domain.Order;
import com.getir.readingisgood.api.core.model.repository.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class CustomerServiceImpl {

    @Autowired
    private OrderRepository orderRepository;

    public List<Order> getOrdersByUsername(String username, Integer pageNo, Integer pageSize) throws OrderNotFoundException {

        Pageable pageable = PageRequest.of(pageNo != null ? pageNo : 0, pageSize != null ? pageSize : 10);
        Page<Order> ordersByUsername = orderRepository.findOrdersByUsername(username, pageable);

        if(ordersByUsername.isEmpty()){
            throw new OrderNotFoundException("User " + username + " does not have any orders" );
        }

        return ordersByUsername.getContent();
    }


}
