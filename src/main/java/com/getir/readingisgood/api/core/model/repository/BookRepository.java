package com.getir.readingisgood.api.core.model.repository;

import com.getir.readingisgood.api.core.model.domain.Book;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookRepository extends MongoRepository<Book, String> {
     Optional<Book> findBookByBookName(String bookName);
}
