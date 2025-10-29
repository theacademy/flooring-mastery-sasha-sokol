package com.flooringmastery.dao;

import java.util.List;

import com.flooringmastery.dto.Product;

public interface FlooringMasteryProductsDao {

    public List<Product> getAll();

    public Product getProduct(String productType);
    
}
