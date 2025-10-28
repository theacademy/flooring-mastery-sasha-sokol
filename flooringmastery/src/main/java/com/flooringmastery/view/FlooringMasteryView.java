package com.flooringmastery.view;

import java.math.BigDecimal;
import java.text.MessageFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.flooringmastery.dto.Product;

@Component
public class FlooringMasteryView {

    @Autowired
    private UserIO io;

    public int getChoiceMainMenu() {
        io.print("  * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *");
        io.print("  * <<Flooring Program>>");
        io.print("  * 1. Display Orders");
        io.print("  * 2. Add an Order");
        io.print("  * 3. Edit an Order");
        io.print("  * 4. Remove an Order");
        io.print("  * 5. Export All Data");
        io.print("  * 6. Quit");
        io.print("  *");
        io.print("  * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *");
        return io.readInt("Enter choice (1-6)", 1, 6);
    }

    public void displayOrdersHeader() {
        io.print("  * <<Display Orders>>");
        io.print("  * Shows all the orders for a given date.");
    }

    public void addOrderHeader() {
        io.print("  * <<Add Order>>");
        io.print("  * Creates a new order with the given data.");
    }

    public void editOrderHeader() {
        io.print("  * <<Edit Order>>");
        io.print("  * Edits the details of an existing order.");
    }

    public void removeOrderHeader() {
        io.print("  * <<Remove Order>>");
        io.print("  * Removes an existing order.");
    }

    public String getDate() {
        return io.readString("Enter date (MMDDYY format)");
    }

    public String getName() {
        return io.readString("Enter customer name");
    }

    public String getStateAbbr() {
        return io.readString("Enter state abbreviation");
    }

    public Product chooseProductType(List<Product> products) {
        int offset = 1;  // indexing
        int min = 0 + offset;
        int max = products.size() - 1 + offset;

        // Display list of products

        var choice = io.readInt(MessageFormat.format("Enter choice ({0}-{1})", min, max), min, max);

        return products.get(choice - offset);
    }

    public BigDecimal getArea() {
        return io.readDecimal("Enter area (in sq ft)", new BigDecimal(0), new BigDecimal(100));
    }

}
