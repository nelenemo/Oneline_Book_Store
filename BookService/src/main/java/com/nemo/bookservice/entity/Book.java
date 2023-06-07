package com.nemo.bookservice.entity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.nemo.bookservice.enums.BookStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bookId;
    private String title;
    private String authors;
    private String publishingHouse;
    private Double retailPrice;
    @Enumerated (EnumType.STRING)
    private BookStatus bookStatus;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "book_category_id")
    @JsonBackReference
    private BookCategory bookcategory;
    private int numberOfBooksAvailable;
}