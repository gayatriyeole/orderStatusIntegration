package com.orderHub.orderStatusIntegration.util;

import com.orderHub.orderStatusIntegration.model.Order;
import com.orderHub.orderStatusIntegration.model.SystemAOrder;
import com.orderHub.orderStatusIntegration.model.SystemBOrder;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class OrderMapper {

    private static final DateTimeFormatter SYSTEM_B_DATE =
            DateTimeFormatter.ofPattern("MM/dd/yyyy");

    public static Order fromSystemA(SystemAOrder orderA) {
        Order order = new Order();
        order.setOrderId(orderA.getOrderID());
        order.setSourceSystem("SystemA");
        order.setCustomerName(orderA.getCustomer());
        order.setOrderDate(LocalDate.parse(orderA.getOrderDate()));
        order.setTotalAmount(orderA.getTotalAmount());
        order.setStatus(StatusMapper.fromSystemA(orderA.getStatus()));
        return order;
    }

    public static Order fromSystemB(SystemBOrder orderB) {
        Order order = new Order();
        order.setOrderId(orderB.getOrderNum());
        order.setSourceSystem("SystemB");
        order.setCustomerName(orderB.getClientName());
        order.setOrderDate(LocalDate.parse(orderB.getDatePlaced(), SYSTEM_B_DATE));
        order.setTotalAmount(orderB.getTotal());
        order.setStatus(StatusMapper.fromSystemB(orderB.getOrderStatus()));
        return order;
    }
}
