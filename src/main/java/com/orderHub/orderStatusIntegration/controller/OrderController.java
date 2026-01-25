package com.orderHub.orderStatusIntegration.controller;

import com.orderHub.orderStatusIntegration.model.Order;
import com.orderHub.orderStatusIntegration.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }


    //To check health
    @GetMapping("/health")
    public ResponseEntity<String> health() {

        boolean healthy = orderService.isOrdersLoaded();

        return healthy
                ? ResponseEntity.ok("HEALTHY")
                : ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body("UNHEALTHY");
    }

    //To get all orders
    @GetMapping("/orders")
    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }

    //To find the order by orderId
    @GetMapping("/orders/{orderId}")
    public ResponseEntity<Order> getOrder(@PathVariable String orderId) {
        return orderService.getOrderById(orderId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    //To find the order by status
    @GetMapping("/orders/search")
    public List<Order> searchByStatus(@RequestParam String status) {
        return orderService.getOrdersByStatus(status);
    }

    // To find orders between startDate and endDate
    @GetMapping(value = "/orders/search", params = {"startDate", "endDate"})
    public List<Order> searchByDateRange(@RequestParam String startDate,
            @RequestParam String endDate) {
        return orderService.getOrdersBetweenDates(startDate, endDate);
    }



}