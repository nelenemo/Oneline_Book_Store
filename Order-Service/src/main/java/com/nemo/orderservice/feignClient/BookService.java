package com.nemo.orderservice.feignClient;

import com.nemo.orderservice.dto.BookResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.awt.print.Book;

@FeignClient(name = "BOOK-SERVICE", path = "/api/book/")
public interface BookService {
    @GetMapping("/getBookByNme/{title}")
    public ResponseEntity<BookResponse> getBookByName(@PathVariable("title") String title);

    @PutMapping("/updatePrice/{bookId}")
    public Book UpdateBook(@RequestBody Book newBook, @PathVariable int bookId);


//    @GetMapping("/getProductByName/{name}")
//    ProductResponse getProductByName(@PathVariable String name);
//
//    @PutMapping("/updateProductStock")
//    String updateProductStock(String productName, double quantity);
}
