package com.getir.readingisgood.api.core.model.service;

import com.getir.readingisgood.api.core.exception.BookNotExistException;
import com.getir.readingisgood.api.core.exception.InsufficientBookStockException;
import com.getir.readingisgood.api.core.exception.InvalidOrderAmountException;
import com.getir.readingisgood.api.core.exception.OrderNotFoundException;
import com.getir.readingisgood.api.core.model.domain.Book;
import com.getir.readingisgood.api.core.model.domain.EOrderStatus;
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
            throw new InvalidOrderAmountException("Users cannot order books with negative values");
        }
        if (book.getStockAmount() < orderRequest.getPurchaseAmount()) {
            throw new InsufficientBookStockException("Book stock amount cannot be lower than 0");
        }

        Order newOrder = new Order(customer.getUsername(), orderRequest.getBookId(),
                orderRequest.getPurchaseAmount(), orderRequest.getPurchaseAmount() * book.getPrice());

        BookStockUpdateRequest bookStockUpdateRequest = new BookStockUpdateRequest(book.getBookName(),
                book.getStockAmount() - orderRequest.getPurchaseAmount());

        try {
            bookService.updateBookStock(bookStockUpdateRequest);
        } catch (Exception e) {
            e.printStackTrace();
        }
        orderRepository.save(newOrder);

        log.info(LocalDateTime.now() + " New order creation: " + newOrder.toString());

        return newOrder;
    }

    public Order getOrderById(String orderId) throws OrderNotFoundException {

        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException("Incorrect order id"));

        return order;
    }

    public List<Order> getOrdersByDateInterval(LocalDateTime startDate, LocalDateTime endDate) throws OrderNotFoundException {

        List<Order> orders = orderRepository.findOrdersByOrderCreationDateBetween(startDate, endDate)
                .orElseThrow(() -> new OrderNotFoundException("There is no given order between this interval"));

        return orders;
    }

    public List<Order> getOrdersByUsername(String username) throws OrderNotFoundException{

        List<Order> orders = orderRepository.findOrdersByUsername(username).orElseThrow(() ->
                new OrderNotFoundException("User" + username + "has no orders"));

        return orders;
    }

    public Order changeOrderStatus(String orderId, OrderRequest orderRequest, EOrderStatus orderStatus) {

        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException("Incorrect order id"));

        Book book = bookService.getBookById(orderRequest.getBookId());

        if(orderStatus.equals(EOrderStatus.REJECTED)) {
            BookStockUpdateRequest bookStockUpdateRequest = new BookStockUpdateRequest(book.getBookName(),
                    book.getStockAmount() + orderRequest.getPurchaseAmount());

            try {
                bookService.updateBookStock(bookStockUpdateRequest);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        order.setOrderStatus(orderStatus);
        orderRepository.save(order);

        log.info(LocalDateTime.now() + " Order "+ order.getId() + " is " + orderStatus + " by the admin");

        return order;
    }



}
