package flooringmastery;

import flooringmastery.controller.FlooringMasteryController;

public class App {
    public static void main(String[] args) throws Exception {
        var controller = new FlooringMasteryController();
        controller.run();
    }
}
