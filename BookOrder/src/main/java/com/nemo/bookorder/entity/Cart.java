package com.nemo.bookorder.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Cart {
    @Id
    private int cartId;
    private int userId;
    private Integer totalItem;
    private Double totalCost;
    private String books; //whatever book we are getting from book service, save it in a db and save whole response as astring in the db

}
