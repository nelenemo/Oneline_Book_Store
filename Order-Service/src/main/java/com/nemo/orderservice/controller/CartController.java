package com.nemo.orderservice.controller;


import com.nemo.orderservice.dto.CartResponse;
import com.nemo.orderservice.entity.CartItem;
import com.nemo.orderservice.service.CartService;
import com.nemo.orderservice.util.AuthUtil;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/carts")
public class CartController {

    private final CartService cartService;
    private final AuthUtil authUtil;

    public CartController(CartService cartService, AuthUtil authUtil) {
        this.cartService = cartService;
        this.authUtil = authUtil;
    }

    @PostMapping("/addToCart")
    public String addProductToCart(
            @RequestHeader("Authorization") String authorizationHeader,
            @RequestBody CartItem cartItem) {

        String userName = authUtil.getUserName(authorizationHeader);

        if (userName == null) {
            return "error";
        }
//        String userName = "don";
        cartService.saveToCart(cartItem, userName);
        return "saved success";
    }

    @GetMapping("/viewMyCart")
    public CartResponse viewMyCart(
            @RequestHeader("Authorization") String authorizationHeader) {

        String userName = authUtil.getUserName(authorizationHeader);
        if(userName!=null){
            return cartService.getMyCart(userName);
        }
        return new CartResponse();
    }

}


