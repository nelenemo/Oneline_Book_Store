package com.nemo.bookorder.entity;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
@ToString
public class Book {
    private int bookId;
    private String title;
    private String authors;
    private String publishingHouse;
    private Integer numberOfBooksAvailable;
    private Double retailPrice;


}
