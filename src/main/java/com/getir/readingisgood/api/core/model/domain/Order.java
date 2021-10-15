package com.getir.readingisgood.api.core.model.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "orders")
public class Order {

    @Id
    private String id;

    @NotBlank
    private String username;

    @NotBlank
    private String bookId;

    @Positive
    private Integer purchaseAmount;

    private EOrderStatus orderStatus;

    private LocalDateTime orderCreationDate;

    @Positive
    private Double totalPrice;

    public Order(String username, String bookId, Integer purchaseAmount, Double totalPrice) {
        this.username = username;
        this.bookId = bookId;
        this.purchaseAmount = purchaseAmount;
        this.orderStatus = EOrderStatus.WAITING;
        this.totalPrice = totalPrice;
        this.orderCreationDate = LocalDateTime.now();
    }

}
