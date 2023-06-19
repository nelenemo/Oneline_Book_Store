package com.nemo.orderservice.dto.paymentDTO;

import com.nemo.orderservice.enums.TransactionStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

public class PaymentDTO {

    private int id;
    private double amount;
    private String account;
    private String transactionId;
    @Enumerated(EnumType.STRING)
    private TransactionStatus transactionStatus;
}
