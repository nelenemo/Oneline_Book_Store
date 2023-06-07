package com.nemo.bookservice.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.nemo.bookservice.entity.BookCategory;
import com.nemo.bookservice.enums.BookStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookRequest {

    private String title;
    private String authors;
    private String publishingHouse;
    private Double retailPrice;
    @Enumerated(EnumType.STRING)
    private BookStatus bookStatus;
    private BookCategory bookcategory;


}
