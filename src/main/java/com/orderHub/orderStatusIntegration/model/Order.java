package com.orderHub.orderStatusIntegration.model;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter

public class Order {
    private String orderId;
    private String sourceSystem;
    private String customerName;
    private LocalDate orderDate;
    private BigDecimal totalAmount;
    private String status;


}