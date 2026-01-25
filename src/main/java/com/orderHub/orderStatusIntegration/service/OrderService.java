package com.orderHub.orderStatusIntegration.service;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import com.orderHub.orderStatusIntegration.model.Order;
import com.orderHub.orderStatusIntegration.model.SystemAOrder;
import com.orderHub.orderStatusIntegration.model.SystemBOrder;
import com.orderHub.orderStatusIntegration.util.OrderMapper;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;
import tools.jackson.databind.ObjectMapper;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    private final List<Order> orders = new ArrayList<>();
    private final ObjectMapper objectMapper;

    public OrderService(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @PostConstruct
    public void loadOrders() {
        orders.clear();
        orders.addAll(loadSystemAOrders());
        orders.addAll(loadSystemBOrders());
    }

    //To check health
    public boolean isOrdersLoaded() {
        return orders != null && !orders.isEmpty();
    }

    //To get all orders
    public List<Order> getAllOrders() {
        return orders; // To return List.copyOf(orders)
    }

    //To find order by Id
    public Optional<Order> getOrderById(String orderId) {
        return orders.stream()
                .filter(o -> o.getOrderId() != null && o.getOrderId().equalsIgnoreCase(orderId))
                .findFirst();
    }

    //To get orders by status
    public List<Order> getOrdersByStatus(String status) {
        return orders.stream()
                .filter(o -> o.getStatus() != null && o.getStatus().equalsIgnoreCase(status))
                .toList();
    }

    //To get orders between start and end date.
    public List<Order> getOrdersBetweenDates(String startDate, String endDate) {
        List<Order> result = new ArrayList<>();
        try {
            LocalDate start = LocalDate.parse(startDate);
            LocalDate end = LocalDate.parse(endDate);
            for (Order order : orders) {
                if (order.getOrderDate() == null) {
                    continue;
                }
                LocalDate orderDate = order.getOrderDate();
                if ((orderDate.isEqual(start) || orderDate.isAfter(start)) && (orderDate.isEqual(end) || orderDate.isBefore(end))) {
                    result.add(order);
                }
            }
        } catch (DateTimeException e) {
            System.err.println("Invalid date: " + e.getMessage());
        }
        return result;
    }


    //To read json file data
    private List<Order> loadSystemAOrders() {
        try (InputStream is = getClass().getResourceAsStream("/data/system_a_orders.json")) {
            if (is == null) return List.of();

            return Arrays.stream(objectMapper.readValue(is, SystemAOrder[].class))
                    .map(OrderMapper::fromSystemA)
                    .toList();

        } catch (IOException e) {
            throw new UncheckedIOException("Failed to load SystemA orders", e);
        }
    }

    // To read csv file data
    private List<Order> loadSystemBOrders() {
        try (InputStream is = getClass().getResourceAsStream("/data/system_b_orders.csv")) {
            if (is == null) return List.of();

            try (CSVReader reader = new CSVReader(new InputStreamReader(is, StandardCharsets.UTF_8))) {
                reader.readNext();

                List<Order> result = new ArrayList<>();
                String[] row;

                while ((row = reader.readNext()) != null) {
                    SystemBOrder order = new SystemBOrder();
                    order.setOrderNum(row[0]);
                    order.setClientName(row[1]);
                    order.setDatePlaced(row[2]);
                    order.setTotal(new BigDecimal(row[3]));
                    order.setOrderStatus(Integer.parseInt(row[4]));

                    result.add(OrderMapper.fromSystemB(order));
                }

                return result;
            }

        } catch (IOException e) {
            throw new UncheckedIOException("Failed to load SystemB orders", e);
        } catch (CsvValidationException e) {
            throw new RuntimeException("Failed to load SystemB orders", e);
        }

    }
}
