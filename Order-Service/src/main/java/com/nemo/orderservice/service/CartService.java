package com.nemo.orderservice.service;

import com.nemo.orderservice.dto.CartResponse;
import com.nemo.orderservice.entity.CartItem;

public interface CartService {

    void saveToCart(CartItem cartItem, String userName);

    CartResponse getMyCart(String userName);
}
