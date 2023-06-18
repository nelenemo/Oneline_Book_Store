package com.nemo.bookorder.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nemo.bookorder.dto.ShoppingCartRequest;
import com.nemo.bookorder.dto.ShoppingCartResponse;
import com.nemo.bookorder.entity.Book;
import com.nemo.bookorder.entity.Cart;
import com.nemo.bookorder.repo.CartRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ShoppingCartServiceImpl implements ShoppingCartService {
    //call book-service
    //calculate totalcost
    //create, save cartentity
    //create api response
    @Autowired
    public WebClient.Builder webBuilder;
    @Autowired
    public CartRepository cartRepository;
    ObjectMapper mapper = new ObjectMapper();

    @Override
    public ShoppingCartResponse AddBookToCartReq(int userId, List<ShoppingCartRequest> shoppingCartRequestList) {
        String BookServiceUrl = "http://localhost:7002/api/book/getAllBooks" + shoppingCartRequestList.stream().map(e -> toString().valueOf(e.getBookId()))
                .collect(Collectors.joining(","));
        List<Book> bookServiceList = webBuilder.build()
                .get()
                .uri(BookServiceUrl)
                .retrieve()
                .bodyToFlux(Book.class)
                .collectList()
                .block();

        final Double[] totalCost = {0.0};

        bookServiceList.forEach(bookServiceLise -> {
            shoppingCartRequestList.forEach(shoppingCatRequest -> {
                if (bookServiceLise.getBookId() == shoppingCatRequest.getBookId()) {
                    bookServiceLise.setNumberOfBooksAvailable(shoppingCatRequest.getQuantity());
                    totalCost[0] = totalCost[0] + bookServiceLise.getRetailPrice() * shoppingCatRequest.getQuantity();

                }
            });
        });
        Cart cart = null;
        try {
            cart = Cart.builder()
                    .userId(userId)
                    .cartId((int) (Math.random() * Math.pow(10, 10)))
                    .totalItem(bookServiceList.size())
                    .totalCost(totalCost[0])
                    .books(mapper.writeValueAsString(bookServiceList))
                    .build();

        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);

        }
        cart = cartRepository.save(cart);
        ShoppingCartResponse response = ShoppingCartResponse.builder()
                .cartId(cart.getCartId())
                .userId(cart.getUserId())
                .totalItem(cart.getTotalItem())
                .totalCost(cart.getTotalCost())
                .books(bookServiceList)
                .build();
        return response;
    }

    @Override
    public List<ShoppingCartResponse> getShoppingCart(int userId) {
        ObjectMapper mapper = new ObjectMapper();
        List<Cart> carts = cartRepository.findByUserId(userId);
        List<ShoppingCartResponse> cartResponses = carts.stream()
                .map(cartt -> {
                    try {
                        return ShoppingCartResponse.builder()
                                .cartId(cartt.getCartId())
                                .userId(cartt.getUserId())
                                .totalItem(cartt.getTotalItem())
                                .totalCost(cartt.getTotalCost())
                                .books(mapper.readValue(cartt.getBooks(), List.class))
                                .build();
                    } catch (JsonProcessingException e) {
                        throw new RuntimeException(e);

                    }
                }).collect(Collectors.toList());
        return cartResponses;

    }
}







