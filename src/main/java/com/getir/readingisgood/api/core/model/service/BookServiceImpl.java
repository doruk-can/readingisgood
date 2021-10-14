package com.getir.readingisgood.api.core.model.service;

import com.getir.readingisgood.api.core.exception.BookAlreadyExistException;
import com.getir.readingisgood.api.core.exception.BookNotExistException;
import com.getir.readingisgood.api.core.model.domain.Book;
import com.getir.readingisgood.api.core.model.repository.BookRepository;
import com.getir.readingisgood.api.core.payload.BookDto;
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

        Optional<Book> book = bookRepository.findBookByBookName(bookDto.getBookName());

        if(book.isPresent()) {
            throw new BookAlreadyExistException(bookDto.getBookName() + " was already added to the warehouse database");
        }

        Book newBook = new Book(bookDto.getBookName(),
                bookDto.getAuthor(),
                bookDto.getStockAmount(),
                bookDto.getPrice());

        bookRepository.save(newBook);

        log.debug(bookDto.getBookName() + " is added to the warehouse database");

        return newBook;
    }

    //to do: book stock update will be added

}
