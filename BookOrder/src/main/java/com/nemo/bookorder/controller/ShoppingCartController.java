package com.nemo.bookorder.controller;

import com.nemo.bookorder.dto.ShoppingCartRequest;
import com.nemo.bookorder.dto.ShoppingCartResponse;
import com.nemo.bookorder.service.ShoppingCartService;
import org.apache.http.protocol.HTTP;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/shoppingCart")
public class ShoppingCartController {
    private final ShoppingCartService shoppingCartService;

    public ShoppingCartController(ShoppingCartService shoppingCartService) {
        this.shoppingCartService = shoppingCartService;
    }


    @PostMapping("/{userId}/BookToCart")
    public ResponseEntity addBookToCart(
            @PathVariable int userId,
            @RequestBody List<ShoppingCartRequest> requestBookList

    ) {
        ShoppingCartResponse response=shoppingCartService.AddBookToCartReq(userId, requestBookList);
    return new ResponseEntity(response, HttpStatus.CREATED);
    }

    @GetMapping("/{userId}")
    public ResponseEntity getShoppingCart(@PathVariable int userId){
        return ResponseEntity.ok(shoppingCartService.getShoppingCart(userId));
    }


}
