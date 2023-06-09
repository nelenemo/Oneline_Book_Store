package com.nemo.bookservice.repository;

import com.nemo.bookservice.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Integer>{

    Optional<Book> findBookByTitle (String title);
    Optional<Book> findBookByAuthors (String authors);


}
