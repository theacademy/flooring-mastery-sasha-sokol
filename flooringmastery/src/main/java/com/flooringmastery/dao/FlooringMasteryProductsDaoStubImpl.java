package com.flooringmastery.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.flooringmastery.dto.Product;

public class FlooringMasteryProductsDaoStubImpl implements FlooringMasteryProductsDao {

    @Override
    public List<Product> getAll() {
        return new ArrayList<>();
    }

    @Override
    public Product getProduct(String productType) {
        var p = new Product(productType);
        p.setCostPerSquareFoot(new BigDecimal(5));
        p.setLaborCostPerSquareFoot(new BigDecimal(5));
        return p;
    }

}
