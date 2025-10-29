package com.flooringmastery.dao;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.springframework.stereotype.Component;

import com.flooringmastery.dto.Product;

@Component
public final class FlooringMasteryProductsDaoFileImpl implements FlooringMasteryProductsDao {

    private static final String PRODUCTS_FILE = "sample/Data/Products.txt";
    private static final String DELIMITER = ",";

    private List<Product> products = new ArrayList<>();

    public FlooringMasteryProductsDaoFileImpl() throws FileNotFoundException {
        loadData();
    }

    public void loadData() throws FileNotFoundException {
        var sc = new Scanner(new BufferedReader(new FileReader(PRODUCTS_FILE)));

        sc.nextLine();  // Skip header row

        while (sc.hasNextLine()) {
            unmarshallLine(sc.nextLine());
        }
    }

    private void unmarshallLine(String line) {
        String[] tokens = line.split(DELIMITER);
        Product p = new Product(tokens[0]);
        p.setCostPerSquareFoot(new BigDecimal(tokens[1]));
        p.setLaborCostPerSquareFoot(new BigDecimal (tokens[2]));
        products.add(p);
    }

    @Override
    public List<Product> getAll() {
        return products;
    }

    @Override
    public Product getProduct(String productType) {
        var matchingProducts = products.stream().filter(p -> p.getProductType().equals(productType));
        return matchingProducts.findFirst().get();
    }

}
