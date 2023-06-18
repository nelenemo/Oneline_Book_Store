package com.nemo.orderservice.entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int cartId;
    private int userId;
    private String name;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "cart")
    private List<CartItem> cartItems;
}
