package com.getir.readingisgood.api.v1;

import com.getir.readingisgood.api.core.exception.BookAlreadyExistException;
import com.getir.readingisgood.api.core.exception.BookNotExistException;
import com.getir.readingisgood.api.core.model.domain.Book;
import com.getir.readingisgood.api.core.model.service.BookServiceImpl;
import com.getir.readingisgood.api.core.payload.BookDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1/book")
public class BookController {

    @Autowired
    private BookServiceImpl bookService;

    @GetMapping("/find")
    public ResponseEntity<Book> getBookById(@RequestParam String bookId){

        Book book = bookService.getBookById(bookId);

        return ResponseEntity.ok(book);
    }

    @PostMapping("/add")
    public ResponseEntity<Book> addNewBook(@RequestBody BookDto bookDto) {

        Book newBook = bookService.addNewBook(bookDto);

        return ResponseEntity.ok(newBook);
    }
}
