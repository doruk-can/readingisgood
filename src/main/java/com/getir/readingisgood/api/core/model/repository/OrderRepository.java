package com.getir.readingisgood.api.core.model.repository;

import com.getir.readingisgood.api.core.model.domain.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends MongoRepository<Order, String> {

    Optional<List<Order>> findOrdersByOrderCreationDateBetween(LocalDateTime startDate, LocalDateTime endDate);
    Page<Order> findOrdersByUsername(String username, Pageable pageable);
    Optional<List<Order>> findOrdersByUsername(String username);

}
