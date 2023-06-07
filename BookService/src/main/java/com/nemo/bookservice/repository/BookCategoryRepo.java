package com.nemo.bookservice.repository;

import com.nemo.bookservice.entity.BookCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BookCategoryRepo extends JpaRepository<BookCategory, Integer> {

    Optional<BookCategory>  findBookCategoriesByCategoryName (String categoryName);
}
