package com.nemo.bookorder.dto;

import com.nemo.bookorder.entity.Book;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ShoppingCartResponse {
    private int cartId;
    private int userId;
    private Integer totalItem;
    private Double totalCost;
    private List<Book> books;
}
