package com.getir.readingisgood;

import com.getir.readingisgood.api.core.model.domain.EOrderStatus;
import com.getir.readingisgood.api.core.model.domain.Order;
import com.getir.readingisgood.api.core.model.repository.OrderRepository;
import com.getir.readingisgood.api.core.model.service.BookServiceImpl;
import com.getir.readingisgood.api.core.model.service.CustomerServiceImpl;
import com.getir.readingisgood.api.core.model.service.OrderServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {OrderServiceImpl.class})
public class OrderServiceTest {

    @Autowired
    private OrderServiceImpl orderService;

    @MockBean
    private OrderRepository orderRepository;

    /*
    @MockBean
    private MongoTemplate mongoTemplate;

    @MockBean
    private BookServiceImpl bookService;

    @MockBean
    private CustomerServiceImpl customerService;

    @Test
    public void getOrderById_success() {

        Order order = new Order( "1" ,"mert", "43", 4, EOrderStatus.APPROVED, LocalDateTime.now(), 84.0);

        when(orderRepository.findById(order.getId())).thenReturn(Optional.of(order));
        Order found = orderService.getOrderById(order.getId());

    }*/

}
