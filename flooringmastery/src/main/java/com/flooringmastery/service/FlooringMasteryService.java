package com.flooringmastery.service;

import java.math.BigDecimal;
import java.util.List;

import com.flooringmastery.dto.Order;
import com.flooringmastery.dto.Product;

public interface FlooringMasteryService {

    public ValidationResponse validateExistingOrderDate(String date);

    public ValidationResponse validateNewOrderDate(String date);

    public ValidationResponse validateCustomerName(String name);

    public ValidationResponse validateStateAbbr(String stateAbbr);

    public ValidationResponse validateArea(BigDecimal area);

    public List<Product> getAllProducts();

    public List<Order> getOrdersByDate(String date);

}
