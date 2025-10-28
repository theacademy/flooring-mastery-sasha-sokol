package com.flooringmastery.service;

import java.math.BigDecimal;
import java.text.MessageFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Optional;

import com.flooringmastery.dto.Order;
import com.flooringmastery.dto.Product;

public class FlooringMasteryServiceImpl implements FlooringMasteryService {

    private final BigDecimal minimumOrderArea = new BigDecimal(100);

    private Optional<LocalDate> parseDate(String s) {
        try {
            return Optional.of(LocalDate.parse(s, DateTimeFormatter.ofPattern("MMddyyyy")));
        }
        catch (DateTimeParseException e) {
            return Optional.empty();
        }
    }

    @Override
    public ValidationResponse validateExistingOrderDate(String date) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public ValidationResponse validateNewOrderDate(String date) {
        var target = parseDate(date);
        
        if (target.isEmpty()) 
            return new ValidationResponse("Date not formatted correctly");
        else if (target.get().isBefore(LocalDate.now()))
            return new ValidationResponse("Date cannot be in the past");
        else
            return new ValidationResponse();
    }

    @Override
    public ValidationResponse validateCustomerName(String name) {
        String specialRegex = "[^a-zA-Z0-9., ]";  // Matches all characters except letters, digits, periods, commas, spaces

        if (name.isBlank())
            return new ValidationResponse("Name cannot be blank");
        else if (name.matches(specialRegex))
            return new ValidationResponse("Name cannot contain special characters");
        else
            return new ValidationResponse();
    }

    @Override
    public ValidationResponse validateStateAbbr(String stateAbbr) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public ValidationResponse validateArea(BigDecimal area) {
        if (area.compareTo(minimumOrderArea) < 0)
            return new ValidationResponse(MessageFormat.format("Area must be at least {0}", minimumOrderArea));
        else
            return new ValidationResponse();
    }

    @Override
    public List<Product> getAllProducts() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Order> getOrdersByDate(String date) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Optional<Order> getSingleOrder(String date, int orderNumber) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void calculateMissingFields(Order order) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void createOrder(Order order) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void updateOrder(Order order) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void deleteOrder(Order order) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
