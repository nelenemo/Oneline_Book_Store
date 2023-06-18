package com.nemo.orderservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int cartItemId;

    private String title;
//    private double orderedQuantity;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "cart_id")
    private Cart cart;
}
