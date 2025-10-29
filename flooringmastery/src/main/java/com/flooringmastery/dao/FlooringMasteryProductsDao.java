package com.flooringmastery.dao;

import java.math.BigDecimal;
import java.util.List;

import com.flooringmastery.dto.Product;

public interface FlooringMasteryProductsDao {

    public List<Product> getAll();

    public BigDecimal getCostPerSqFt(Product product);

    public BigDecimal getLaborCostPerSqFt(Product product);
    
}
