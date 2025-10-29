package com.flooringmastery.dao;

import java.util.List;
import java.util.Optional;

import com.flooringmastery.dto.Order;

public interface FlooringMasteryDao {

    public List<Order> getOrdersByDate(String date);

    public Optional<Order> getOrder(String date, int orderNumber);

    public void create(Order order);

    public void update(Order order);

    public void delete(Order order);
    
}
