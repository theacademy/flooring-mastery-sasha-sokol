package com.flooringmastery.controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.flooringmastery.dto.Order;
import com.flooringmastery.dto.Product;
import com.flooringmastery.service.FlooringMasteryService;
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
        List<Product> products = service.getAllProducts();
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

        Order order = new Order();
        order.setDate(date);
        order.setCustomerName(name);
        order.setStateAbbr(stateAbbr);
        order.setProductType(product);
        order.setArea(area);

        service.calculateMissingFields(order);

        view.confirmAddOrder(order);

        service.createOrder(order);
    }

    private void editOrder() {
        view.editOrderHeader();

        String date;
        int orderNumber;
        Order order = null;

        // Date
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

        // Order number
        finished = false;
        do {
            orderNumber = view.getOrderNumber();
            var response = service.getSingleOrder(date, orderNumber);
            if (response.isPresent()) {
                order = response.get();
                finished = true;
            }
            else {
                view.displayError("No order with that number exists.");
                if (view.askCancel()) return;
            }
        } while (!finished);


        List<Product> products = service.getAllProducts();


        // Customer name
        finished = false;
        do {
            var newName = view.editName(order.getCustomerName());
            if (newName.isPresent()) {
                var response = service.validateCustomerName(newName.get());
                if (response.valid) {
                    order.setCustomerName(newName.get());
                    finished = true;
                }
                else {
                    view.displayError(response.message);
                    if (view.askCancel()) return;
                }
            }
            else {
                finished = true;
            }
        } while (!finished);

        // State
        finished = false;
        do {
            var newState = view.editStateAbbr(order.getStateAbbr());
            if (newState.isPresent()) {
                var response = service.validateStateAbbr(newState.get());
                if (response.valid) {
                    order.setStateAbbr(newState.get());
                    finished = true;
                }
                else {
                    view.displayError(response.message);
                    if (view.askCancel()) return;
                }
            }
            else {
                finished = true;
            }
        } while (!finished);

        // Product
        var newProduct = view.editProduct(order.getProductType(), products);
        if (newProduct.isPresent()) {
            order.setProductType(newProduct.get());
        }

        // Area
        finished = false;
        do {
            var newArea = view.editArea(order.getArea());
            if (newArea.isPresent()) {
                var response = service.validateArea(newArea.get());
                if (response.valid) {
                    order.setArea(newArea.get());
                    finished = true;
                }
                else {
                    view.displayError(response.message);
                    if (view.askCancel()) return;
                }
            }
            else {
                finished = true;
            }
        } while (!finished);


        service.calculateMissingFields(order);

        view.confirmEditOrder(order);

        service.updateOrder(order);

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
