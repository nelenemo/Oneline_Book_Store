package com.nemo.orderservice.dto;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
    private int numberOfBooksAvailable;

}
