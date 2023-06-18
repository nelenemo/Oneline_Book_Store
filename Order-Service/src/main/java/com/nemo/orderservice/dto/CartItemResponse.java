package com.nemo.orderservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CartItemResponse {
    private String title;
//    private String productDescription;
//    private double orderedQuantity;
    private double price;
}
