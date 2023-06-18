package com.nemo.bookorder.service;

import com.nemo.bookorder.dto.ShoppingCartRequest;
import com.nemo.bookorder.dto.ShoppingCartResponse;

import java.util.List;

public interface ShoppingCartService {
    ShoppingCartResponse AddBookToCartReq(int userId, List<ShoppingCartRequest> requestBookList);

    List<ShoppingCartResponse> getShoppingCart(int userId);
}
