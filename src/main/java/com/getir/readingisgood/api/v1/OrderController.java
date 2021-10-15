package com.getir.readingisgood.api.v1;

import com.getir.readingisgood.api.core.exception.BookNotExistException;
import com.getir.readingisgood.api.core.exception.OrderNotFoundException;
import com.getir.readingisgood.api.core.model.domain.Order;
import com.getir.readingisgood.api.core.model.service.OrderServiceImpl;
import com.getir.readingisgood.api.core.payload.request.OrderRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1/order")
public class OrderController {

    @Autowired
    private OrderServiceImpl orderService;

    @GetMapping("/find")
    public ResponseEntity<Order> getOrderById(@RequestParam String orderId) {

        Order order = orderService.getOrderById(orderId);
        return ResponseEntity.ok(order);
    }

    @GetMapping("/listByInterval")
    public ResponseEntity<List<Order>> getOrdersByDateInterval(@RequestParam @DateTimeFormat(iso= DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
                                                              @RequestParam @DateTimeFormat(iso= DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate) {

        List<Order> orders = orderService.getOrdersByDateInterval(startDate, endDate);

        return ResponseEntity.ok(orders);
    }

    @PostMapping("/create")
    public ResponseEntity<Order> createNewOrder(@RequestBody OrderRequest orderRequest) {

        Order newOrder = orderService.createNewOrder(orderRequest);

        return ResponseEntity.ok(newOrder);
    }

}
