package com.flooringmastery.service;

import java.util.List;

import com.flooringmastery.dto.Order;

public interface FlooringMasteryService {

    public ValidationResponse validateExistingOrderDate(String date);

    public List<Order> getOrdersByDate(String date);

}
