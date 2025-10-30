package com.flooringmastery.service;

import java.math.BigDecimal;
import java.text.MessageFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.flooringmastery.dao.FlooringMasteryDao;
import com.flooringmastery.dao.FlooringMasteryProductsDao;
import com.flooringmastery.dao.FlooringMasteryTaxDao;
import com.flooringmastery.dto.Order;
import com.flooringmastery.dto.Product;

@Component
public class FlooringMasteryServiceImpl implements FlooringMasteryService {

    @Autowired
    FlooringMasteryDao dao;

    @Autowired
    FlooringMasteryProductsDao productsDao;

    @Autowired
    FlooringMasteryTaxDao taxDao;

    private final BigDecimal minimumOrderArea = new BigDecimal(100);

    public FlooringMasteryServiceImpl() {

    }

    public FlooringMasteryServiceImpl(FlooringMasteryDao dao, FlooringMasteryProductsDao productsDao, FlooringMasteryTaxDao taxDao) {
        this.dao = dao;
        this.productsDao = productsDao;
        this.taxDao = taxDao;
    }

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
        if (parseDate(date).isEmpty())
            return new ValidationResponse("Date not formatted correctly");
        else if (dao.getOrdersByDate(date).isEmpty())
            return new ValidationResponse("No orders for that date");
        else
            return new ValidationResponse();
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
        String specialRegex = ".*[^a-zA-Z0-9. ].*";  // Matches any string with a character except letters, numbers, periods, spaces
        if (name.isBlank())
            return new ValidationResponse("Name cannot be blank");
        else if (name.matches(specialRegex))
            return new ValidationResponse("Name cannot contain special characters");
        else
            return new ValidationResponse();
    }

    @Override
    public ValidationResponse validateStateAbbr(String stateAbbr) {
        if(!taxDao.exists(stateAbbr))
            return new ValidationResponse(MessageFormat.format("Cannot sell to state {0}", stateAbbr));
        else
            return new ValidationResponse();
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
        return productsDao.getAll();
    }

    @Override
    public List<Order> getOrdersByDate(String date) {
        return dao.getOrdersByDate(date);
    }

    @Override
    public Optional<Order> getSingleOrder(String date, int orderNumber) {
        return dao.getOrder(date, orderNumber);
    }

    @Override
    public void calculateMissingFields(Order order) {
        var stateAbbr = order.getStateAbbr();
        order.setTaxRate(taxDao.getTaxRate(stateAbbr));

        var product = order.getProduct();
        order.setCostPerSqFt(product.getCostPerSquareFoot());
        order.setLaborCostPerSqFt(product.getLaborCostPerSquareFoot());

        var area = order.getArea();
        order.setMaterialCost(area.multiply(order.getCostPerSqFt()));
        order.setLaborCost(area.multiply(order.getLaborCostPerSqFt()));

        order.setTax(order.getMaterialCost().add(order.getLaborCost())
            .multiply(order.getTaxRate().divide(new BigDecimal(100))));

        order.setTotal(order.getMaterialCost().add(order.getLaborCost())
            .add(order.getTax()));
    }

    @Override
    public void createOrder(Order order) {
        dao.create(order);
    }

    @Override
    public void updateOrder(Order order) {
        dao.update(order);
    }

    @Override
    public void deleteOrder(Order order) {
        dao.delete(order);
    }

}
