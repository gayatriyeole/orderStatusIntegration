package com.orderHub.orderStatusIntegration.model;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
@Getter
@Setter
public class SystemBOrder {

    private String orderNum;
    private String clientName;
    private String datePlaced;
    private BigDecimal total;
    private int orderStatus;
}
