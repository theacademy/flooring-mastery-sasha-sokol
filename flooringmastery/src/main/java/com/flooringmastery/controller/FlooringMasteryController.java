package com.flooringmastery.controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.flooringmastery.dto.Order;
import com.flooringmastery.dto.Product;
import com.flooringmastery.service.FlooringMasteryService;
import com.flooringmastery.service.ValidationResponse;
import com.flooringmastery.view.FlooringMasteryView;

@Component
public class FlooringMasteryController {

    @Autowired
    private FlooringMasteryView view;

    @Autowired
    private FlooringMasteryService service;

    public void run() {
        while (true) {
            int op = chooseOperationMainMenu();

            switch (op) {
                case 1:
                    displayOrders();
                    break;
                case 2:
                    addOrder();
                    break;
                case 3:
                    editOrder();
                    break;
                case 4:
                    removeOrder();
                    break;
                case 5:
                    exportData();
                    break;
                case 6:
                    quit();
                    return;
            }
        }
    }

    private int chooseOperationMainMenu() {
        return view.getChoiceMainMenu();
    }

    private void displayOrders() {
        view.displayOrdersHeader();

        String date;

        boolean finished = false;
        do {
            date = view.getDate();
            var response = service.validateExistingOrderDate(date);
            if (response.valid) {
                finished = true;
            }
            else {
                view.displayError(response.message);
                if (view.askCancel()) return;
            }
        } while (!finished);

        List<Order> orders = service.getOrdersByDate(date);
        view.displayOrderList(orders);
    }

    private void addOrder() {
        view.addOrderHeader();

        String date;
        String name;
        String stateAbbr;
        Product product;
        BigDecimal area;
        
        // Order date
        boolean finished = false;
        do {
            date = view.getDate();
            var response = service.validateNewOrderDate(date);
            if (response.valid) {

                finished = true;
            }
            else {
                view.displayError(response.message);
                if (view.askCancel()) return;
            }
        } while (!finished);

        // Customer name
        finished = false;
        do {
            name = view.getName();
            var response = service.validateCustomerName(name);
            if (response.valid) {
                finished = true;
            }
            else {
                view.displayError(response.message);
                if (view.askCancel()) return;
            }
        } while (!finished);

        // State
        finished = false;
        do {
            stateAbbr = view.getStateAbbr();
            var response = service.validateStateAbbr(stateAbbr);
            if (response.valid) {
                finished = true;
            }
            else {
                view.displayError(response.message);
                if (view.askCancel()) return;
            }
        } while (!finished);

        // Product type
        List<Product> products = ?;
        product = view.chooseProductType(products);

        // Area
        finished = false;
        do {
            area = view.getArea();
            var response = service.validateArea(area);
            if (response.valid) {
                finished = true;
            }
            else {
                view.displayError(response.message);
                if (view.askCancel()) return;
            }
        } while (!finished);


    }

    private void editOrder() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    private void removeOrder() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    private void exportData() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    private void quit() {

    }
}
