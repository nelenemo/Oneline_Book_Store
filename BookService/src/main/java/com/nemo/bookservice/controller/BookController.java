package com.nemo.bookservice.controller;

import com.nemo.bookservice.dto.BookRequest;
import com.nemo.bookservice.dto.BookResponse;
import com.nemo.bookservice.entity.Book;
import com.nemo.bookservice.exception.BookDoesntExistException;
import com.nemo.bookservice.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/book")
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }
//
//    @PostMapping("/addBooks")
//    public String addBooks(@RequestBody Book book){
//        bookService.addBooks(book);
//        return "book has been added";
//    }


    @PostMapping("/addBooks")
    public String addBooks(@RequestBody BookRequest bookRequest) {
        bookService.addBooks(bookRequest);
        return "book has been added";
    }


    @GetMapping("/getAllBooks")
    public ResponseEntity<List<BookResponse>> getAllBooks() {
        List<BookResponse> bookResponses = bookService.getAllBooks();
        return ResponseEntity.status(HttpStatus.OK).body(bookResponses);
    }






    @GetMapping("/getBookById/{bookId}")
    public ResponseEntity<BookResponse> getBookById(@PathVariable("bookId") int bookId) {

        BookResponse book = bookService.getBookById(bookId);

        return ResponseEntity.status(HttpStatus.OK).body(book);
    }
    @GetMapping("/getBookByNme/{title}")
    public ResponseEntity<BookResponse> getBookByName(@PathVariable("title") String title) {

        BookResponse bookByName = bookService.getBookByName(title);

        return ResponseEntity.status(HttpStatus.OK).body(bookByName);
    }

    @PutMapping("/updateBookStock")
    public String updateProductStock(String bookName, int numberOfBooksAvailable){
        bookService.updateStock(bookName,numberOfBooksAvailable);
        return "updated successfully";
    }




//    @PutMapping("/updatePrice/{bookId}")
//    public ResponseEntity<String> updateBookPrice(@PathVariable int bookId, @RequestParam double retailPrice) {
//        try {
//            bookService.updateBookPrice(bookId, retailPrice);
//            return ResponseEntity.ok("Book price updated successfully");
//        } catch (BookDoesntExistException ex) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
//        } catch (Exception ex) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred");
//        }
//    }

    @PutMapping("/updatePrice/{bookId}")
    public Book UpdateBook(@RequestBody Book newBook, @PathVariable int bookId) {
        return bookService.updateBook(newBook, bookId);

    }


    @DeleteMapping("/deleteBook/{bookId}")
    public String deleteBook(@PathVariable int bookId) {
        bookService.deleteBook(bookId);
        return "Book with book id " + bookId + " has been deleted";
    }

//    @PutMapping("/upadatebooks")
//    private Book update(@RequestBody Book book) {
//        bookService.saveOrUpdate();
//        return book;
//    }


//    @DeleteMapping(value = "deleteBookById/{bookId}")
//    public ResponseEntity<BookResponse> deleteBookById(@PathVariable int id) {
//        bookService.deleteBookById()
//
//        var isRemoved = postService.delete(id);
//
//        if (!isRemoved) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//
//        return new ResponseEntity<>(id, HttpStatus.OK);
//    }

}
