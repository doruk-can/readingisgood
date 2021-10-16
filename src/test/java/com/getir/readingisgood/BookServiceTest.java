package com.getir.readingisgood;


import com.getir.readingisgood.api.core.exception.BookAlreadyExistException;
import com.getir.readingisgood.api.core.exception.BookNotExistException;
import com.getir.readingisgood.api.core.exception.BooksOutOfStockException;
import com.getir.readingisgood.api.core.exception.InvalidOrderAmountException;
import com.getir.readingisgood.api.core.model.domain.Book;
import com.getir.readingisgood.api.core.model.repository.BookRepository;
import com.getir.readingisgood.api.core.model.service.BookServiceImpl;
import com.getir.readingisgood.api.core.payload.request.BookAddRequest;
import com.getir.readingisgood.api.core.payload.request.BookStockUpdateRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {BookServiceImpl.class})
public class BookServiceTest {

    @MockBean
    private BookRepository bookRepository;

    @Autowired
    private BookServiceImpl bookService;

    @Test
    public void getBookById_success() {

        Book book = new Book("1", "The Big Sleep", "Raymond Chandler", 20, 3.99, 1L);

        when(bookRepository.findById(book.getId())).thenReturn(Optional.of(book));
        Book found = bookService.getBookById(book.getId());

        assertEquals(found.getId(), book.getId());
    }

    @Test
    public void getBookById_notExist() {

        Book book = new Book("1", "The Big Sleep", "Raymond Chandler", 20, 3.99, 1L);
        when(bookRepository.findById(book.getId())).thenReturn(Optional.empty());

        Assertions.assertThrows(BookNotExistException.class, () -> {
            bookService.getBookById(book.getId());
        });
    }


   /*
    @Test
    public void addNewBook_success () {

        BookAddRequest bookAddRequest = new BookAddRequest("The Big Sleep", "Raymond Chandler", 20, 3.99);
        when(bookRepository.findBookByBookName(bookAddRequest.getBookName())).thenReturn(Optional.empty());
        Book addedBook = bookService.addNewBook(bookAddRequest);

        assertEquals(addedBook.getBookName(), bookAddRequest.getBookName());
        verify(bookRepository.save(addedBook));

    }

    @Test
    public void addNewBook_bookAlreadyExist() {

        Book book = new Book("1", "The Big Sleep", "Raymond Chandler", 20, 3.99, 1L);
        BookAddRequest bookAddRequest = new BookAddRequest("The Big Sleep", "Raymond Chandler", 20, 3.99);

        when(bookRepository.findBookByBookName(bookAddRequest.getBookName())).thenReturn(Optional.of(book));

        Assertions.assertThrows(BookAlreadyExistException.class, () -> {
            bookService.addNewBook(bookAddRequest);
        });

    }

    @Test
    public void updateBookStock_success()  {

        Book book = new Book("1", "The Big Sleep", "Raymond Chandler", 20, 3.99, 1L);
        when(bookRepository.findById(book.getId())).thenReturn(Optional.of(book));

        BookStockUpdateRequest bookStockUpdateRequest = new BookStockUpdateRequest(book.getId(), 1);

        Book updatedBook = bookService.updateBookStock(bookStockUpdateRequest);

        assertEquals(updatedBook.getId(), book.getId());

        verify(bookRepository).save(updatedBook);
    }

    }*/









}