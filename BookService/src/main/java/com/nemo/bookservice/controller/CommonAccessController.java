package com.nemo.bookservice.controller;

import com.nemo.bookservice.dto.BookResponse;
import com.nemo.bookservice.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/common/access/book")
public class CommonAccessController {
    private final BookService bookService;

    public CommonAccessController(BookService bookService) {
        this.bookService = bookService;
    }


}
