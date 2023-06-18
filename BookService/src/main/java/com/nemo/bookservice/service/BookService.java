package com.nemo.bookservice.service;

import com.nemo.bookservice.dto.BookRequest;
import com.nemo.bookservice.dto.BookResponse;
import com.nemo.bookservice.entity.Book;
import org.modelmapper.ModelMapper;

import java.util.List;

public interface BookService {


//    void addBooks(Book book);
void addBooks(BookRequest bookRequest);


    List<BookResponse> getAllBooks();

    void deleteBook(int bookId);

//    void updateBookPrice(int bookId, double retailPrice);

    Book updateBook(Book newBook, int bookId);

    void saveOrUpdate(Book books, int bookId);

    BookResponse getBookById(int bookId);

    BookResponse getBookByName(String title);

    void updateStock(String bookName, int numberOfBooksAvailable);
}
//
//        Book book=new Book();
//        book.setTitle(bookRequest.getTitle());
//        book.setAuthors(book.getAuthors());
//        book.setBookStatus(book.getBookStatus());
//        book.setRetailPrice(book.getRetailPrice());
//        book.setBookStatus(BookStatus.IN_STOCK);