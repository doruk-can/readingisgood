package com.getir.readingisgood.api.v1;

import com.getir.readingisgood.api.core.model.domain.Order;
import com.getir.readingisgood.api.core.model.service.CustomerServiceImpl;
import com.getir.readingisgood.api.core.model.service.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1/customer")
public class CustomerController {

    @Autowired
    private CustomerServiceImpl customerService;

    @GetMapping("/orders")
    public ResponseEntity<List<Order>> getOrdersByUsername(
            @RequestParam String username,
            @RequestParam(required = false) Integer pageNo,
            @RequestParam(required = false) Integer pageSize
    ){
        List<Order> orders = null;
        try {
            orders = customerService.getOrdersByUsername(username, pageNo, pageSize);
        } catch(Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok(orders);
    }

}
