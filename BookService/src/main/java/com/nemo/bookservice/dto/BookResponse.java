package com.nemo.bookservice.dto;

import com.nemo.bookservice.entity.BookCategory;
import com.nemo.bookservice.enums.BookStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookResponse {
    private int bookId;
    private String title;
    private String authors;
    private String publishingHouse;
    private Double retailPrice;
    @Enumerated(EnumType.STRING)
    private BookStatus bookStatus;
    private BookCategoryResponse bookCategory;
    private int numberOfBooksAvailable;

}


