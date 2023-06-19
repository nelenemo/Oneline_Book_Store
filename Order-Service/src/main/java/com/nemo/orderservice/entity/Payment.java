package com.nemo.orderservice.entity;

import com.nemo.orderservice.enums.TransactionStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private double amount;
    private String account;
    private String transactionId;
    @Enumerated(EnumType.STRING)
    private TransactionStatus transactionStatus;

}
