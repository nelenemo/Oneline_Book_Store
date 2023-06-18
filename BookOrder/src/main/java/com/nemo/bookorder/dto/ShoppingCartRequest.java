package com.nemo.bookorder.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ShoppingCartRequest {
    private int BookId;
    private Integer quantity;

}
