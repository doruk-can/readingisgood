package com.getir.readingisgood.api.v1;

import com.getir.readingisgood.api.core.model.domain.Order;
import com.getir.readingisgood.api.core.model.service.CustomerServiceImpl;
import com.getir.readingisgood.api.core.model.service.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1/customer")
public class CustomerController {

    @Autowired
    private CustomerServiceImpl customerService;

    @GetMapping("/orders")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<List<Order>> getOrdersByUsername(
            @RequestParam String username,
            @RequestParam(required = false) Integer pageNo,
            @RequestParam(required = false) Integer pageSize
    ){

        List<Order> orders = customerService.getOrdersByUsername(username, pageNo, pageSize);

        return ResponseEntity.ok(orders);
    }

}
