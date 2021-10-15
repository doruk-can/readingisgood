package com.getir.readingisgood.api.core.model.service;

import com.getir.readingisgood.api.core.exception.BookAlreadyExistException;
import com.getir.readingisgood.api.core.exception.BookNotExistException;
import com.getir.readingisgood.api.core.exception.BooksOutOfStockException;
import com.getir.readingisgood.api.core.model.domain.Book;
import com.getir.readingisgood.api.core.model.repository.BookRepository;
import com.getir.readingisgood.api.core.payload.BookDto;
import com.getir.readingisgood.api.core.payload.request.BookStockUpdateRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class BookServiceImpl {

    @Autowired
    private BookRepository bookRepository;

    public Book getBookById(String bookId) throws BookNotExistException {

        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new BookNotExistException("The book does not exist in the warehouse database"));

        return book;
    }

    public Book addNewBook(BookDto bookDto) throws BookAlreadyExistException{


            if(bookRepository.existsBookByBookName(bookDto.getBookName())) {
                throw new BookAlreadyExistException("The book was already added into the database");
            }

            Book book = bookRepository.findBookByBookName(bookDto.getBookName());


        Book newBook = new Book(bookDto.getBookName(),
                bookDto.getAuthor(),
                bookDto.getStockAmount(),
                bookDto.getPrice());

        bookRepository.save(newBook);

        log.info("New book addition: " + bookDto.toString());

        return newBook;
    }

    public Book updateBookStock(BookStockUpdateRequest bookStockUpdateRequest) throws BooksOutOfStockException {

        Book book = bookRepository.findBookByBookName(bookStockUpdateRequest.getBookName());

        int updatedStock = book.getStockAmount() + bookStockUpdateRequest.getStockAmount();

        if(updatedStock < 0) {
            throw new BooksOutOfStockException("Update failure due to exceeding amount of stock");
        }


        book.setStockAmount(updatedStock);
        bookRepository.save(book);

        log.info("Book stock is updated, updated inventory: " + book.toString());

        return book;

    }

}
