package com.flooringmastery.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.flooringmastery.view.FlooringMasteryView;

@Component
public class FlooringMasteryController {

    @Autowired
    private FlooringMasteryView view;

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
        throw new UnsupportedOperationException("Not supported yet.");
    }

    private void addOrder() {
        throw new UnsupportedOperationException("Not supported yet.");
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
