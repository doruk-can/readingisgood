package com.getir.readingisgood.api.core.model.service;

import com.getir.readingisgood.api.core.exception.BookNotExistException;
import com.getir.readingisgood.api.core.exception.OrderNotFoundException;
import com.getir.readingisgood.api.core.model.domain.Book;
import com.getir.readingisgood.api.core.model.domain.Order;
import com.getir.readingisgood.api.core.model.domain.User;
import com.getir.readingisgood.api.core.model.repository.OrderRepository;
import com.getir.readingisgood.api.core.payload.request.BookStockUpdateRequest;
import com.getir.readingisgood.api.core.payload.request.OrderRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
public class OrderServiceImpl {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private BookServiceImpl bookService;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    public Order createNewOrder(OrderRequest orderRequest) throws BookNotExistException {

        Book book = bookService.getBookById(orderRequest.getBookId());
        UserDetails customer = userDetailsService.loadUserByUsername(orderRequest.getUsername());

        if(orderRequest.getPurchaseAmount() < 1) {
            // throw invalid order amount
        }
        if (book.getStockAmount() < orderRequest.getPurchaseAmount()) {
            // throw insufficient book stock
        }

        Order newOrder = new Order(customer.getUsername(),
                orderRequest.getBookId(),
                orderRequest.getPurchaseAmount(),
                orderRequest.getPurchaseAmount() * book.getPrice());

        BookStockUpdateRequest bookStockUpdateRequest = new BookStockUpdateRequest(book.getBookName(),
                book.getStockAmount() - orderRequest.getPurchaseAmount());

        try {
            bookService.updateBookStock(bookStockUpdateRequest);
        } catch (Exception e) {
            e.printStackTrace();
        }
        orderRepository.save(newOrder);

        log.debug("New order creation: " + newOrder.toString());

        return newOrder;
    }

    public Order getOrderById(String orderId) throws OrderNotFoundException {

        Order order = orderRepository.findById(orderId).orElseThrow(() ->
                new OrderNotFoundException("Incorrect order id"));

        return order;
    }

    public List<Order> getOrdersByDateInterval(LocalDateTime startDate, LocalDateTime endDate) throws OrderNotFoundException {

        List<Order> orders = orderRepository.findOrdersByOrderCreationDateBetween(startDate, endDate).orElseThrow(() ->
                new OrderNotFoundException("There is no order between given dates"));

        return orders;
    }




}
