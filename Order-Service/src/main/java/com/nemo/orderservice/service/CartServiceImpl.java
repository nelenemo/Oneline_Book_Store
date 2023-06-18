package com.nemo.orderservice.service;

import com.nemo.orderservice.dto.BookResponse;
import com.nemo.orderservice.dto.CartResponse;
import com.nemo.orderservice.entity.Cart;
import com.nemo.orderservice.entity.CartItem;
import com.nemo.orderservice.feignClient.BookService;
import com.nemo.orderservice.feignClient.UserService;
import com.nemo.orderservice.repository.CartItemRepository;
import com.nemo.orderservice.repository.CartRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class CartServiceImpl implements CartService{
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final UserService userService;
    private final BookService bookService;

    public CartServiceImpl(CartRepository cartRepository, CartItemRepository cartItemRepository, UserService userService, BookService bookService) {
        this.cartRepository = cartRepository;
        this.cartItemRepository = cartItemRepository;
        this.userService = userService;
        this.bookService = bookService;
    }


    @Override
    public void saveToCart(CartItem cartItem, String username) {
        String title = cartItem.getTitle();
        BookResponse bookByName = bookService.getBookByName(cartItem.getTitle()).getBody();
        System.out.println(bookByName.getPublishingHouse());
        if (bookByName != null) {
            log.info("Save the new book in the cart");
        }

        Optional<Cart> userCart = cartRepository.findByName(username);

        Cart cart;
        if (userCart.isPresent()) {
            cart = userCart.get();
        } else {
            cart = new Cart();
            cart.setName(username);
        }

        cart.setUserId(userService.getUserId(username));

        CartItem cartItemToSave = new CartItem();
        cartItemToSave.setTitle(cartItem.getTitle());
//        if (bookByName != null && bookByName.getStockQuantity() > cartItem.getOrderedQuantity()) {
//            cartItemToSave.setOrderedQuantity(cartItem.getOrderedQuantity());
//        }
        cartItemToSave.setCart(cart);

        cartItemRepository.save(cartItemToSave);

    }

    @Override
    public CartResponse getMyCart(String userName) {
        return null;
    }
}
