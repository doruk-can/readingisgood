package com.getir.readingisgood.api.v1;

import com.getir.readingisgood.api.core.model.domain.Book;
import com.getir.readingisgood.api.core.model.service.BookServiceImpl;
import com.getir.readingisgood.api.core.payload.request.BookAddRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1/book")
public class BookController {

    @Autowired
    private BookServiceImpl bookService;

    @GetMapping("/find")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<Book> getBookById(@RequestParam String bookId){

        Book book = bookService.getBookById(bookId);

        return ResponseEntity.ok(book);
    }

    @PostMapping("/add")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Book> addNewBook(@RequestBody BookAddRequest bookAddRequest) {

        Book newBook = bookService.addNewBook(bookAddRequest);

        return ResponseEntity.ok(newBook);
    }
}
