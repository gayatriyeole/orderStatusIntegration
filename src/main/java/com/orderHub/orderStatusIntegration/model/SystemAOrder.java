package com.orderHub.orderStatusIntegration.model;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
@Getter
@Setter
public class SystemAOrder {

    private String orderID;
    private String customer;
    private String orderDate;
    private BigDecimal totalAmount;
    private String status;
}
