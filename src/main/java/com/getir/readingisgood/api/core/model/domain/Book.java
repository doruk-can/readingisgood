package com.getir.readingisgood.api.core.model.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

@Document("books")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book {

    @Id
    private String id;

    @NotBlank
    private String bookName;

    @NotBlank
    private String author;

    @PositiveOrZero
    private Integer stockAmount;

    @Positive
    private Double price;

    public Book(String bookName, String author, Integer stockAmount, Double price) {
        this.bookName = bookName;
        this.author = author;
        this.stockAmount = stockAmount;
        this.price = price;
    }
}
