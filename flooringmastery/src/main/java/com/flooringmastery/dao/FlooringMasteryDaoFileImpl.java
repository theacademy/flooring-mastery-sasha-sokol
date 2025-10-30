package com.flooringmastery.dao;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.flooringmastery.dto.Order;

@Component
public class FlooringMasteryDaoFileImpl implements FlooringMasteryDao {

    private final String ORDERS_DIR;
    private static final String DELIMITER = ",";

    DecimalFormat df = new DecimalFormat("####.#########");  // Don't use commas!

    private static String fileName(String date) {
        return "Orders_" + date + ".txt";
    }

    private static final String HEADER = "OrderNumber,CustomerName,State,TaxRate,ProductType,Area,CostPerSquareFoot,LaborCostPerSquareFoot,MaterialCost,LaborCost,Tax,Total";

    @Autowired
    FlooringMasteryProductsDao productsDao;

    public FlooringMasteryDaoFileImpl() {
        ORDERS_DIR = "sample/Orders/";
    }

    public FlooringMasteryDaoFileImpl(String ordersDirectory, FlooringMasteryProductsDao productsDao) {
        ORDERS_DIR = ordersDirectory;
        this.productsDao = productsDao;
    }

    private List<Order> loadOrdersByDate(String date) throws FileNotFoundException {
        var sc = new Scanner(new BufferedReader(new FileReader(ORDERS_DIR + fileName(date))));

        sc.nextLine();  // Skip header row

        List<Order> orders = new ArrayList<>();

        while (sc.hasNextLine()) {
            var order = unmarshallOrder(sc.nextLine());
            order.setDate(date);
            orders.add(order);
        }

        return orders;
    }

    private void writeOrdersByDate(List<Order> orders, String date) throws IOException {
        var out = new PrintWriter(new FileWriter(ORDERS_DIR + fileName(date)));

        out.println(HEADER);

        for (Order o : orders) {
            out.println(marshallOrder(o));
            out.flush();
        }

        out.close();
    }

    private Order unmarshallOrder(String line) {
        String[] tokens = line.split(DELIMITER);
        
        Order o = new Order();
        o.setOrderNumber(Integer.parseInt(tokens[0]));
        o.setCustomerName(tokens[1]);
        o.setStateAbbr(tokens[2]);
        o.setTaxRate(new BigDecimal(tokens[3]));
        o.setProductType(productsDao.getProduct(tokens[4]));
        o.setArea(new BigDecimal(tokens[5]));
        o.setCostPerSqFt(new BigDecimal(tokens[6]));
        o.setLaborCostPerSqFt(new BigDecimal(tokens[7]));
        o.setMaterialCost(new BigDecimal(tokens[8]));
        o.setLaborCost(new BigDecimal(tokens[9]));
        o.setTax(new BigDecimal(tokens[10]));
        o.setTotal(new BigDecimal(tokens[11]));

        return o;
    }

    private String marshallOrder(Order o) {
        return MessageFormat.format("{1}{0}{2}{0}{3}{0}{4}{0}{5}{0}{6}{0}{7}{0}{8}{0}{9}{0}{10}{0}{11}{0}{12}",
            DELIMITER,
            o.getOrderNumber(),
            o.getCustomerName(),
            o.getStateAbbr(),
            df.format(o.getTaxRate()),
            o.getProduct().getProductType(),
            df.format(o.getArea()),
            df.format(o.getCostPerSqFt()),
            df.format(o.getLaborCostPerSqFt()),
            df.format(o.getMaterialCost()),
            df.format(o.getLaborCost()),
            df.format(o.getTax()),
            df.format(o.getTotal()));
    }

    @Override
    public List<Order> getOrdersByDate(String date) {
        try {
            return loadOrdersByDate(date);
        } 
        catch (FileNotFoundException e) {
            return new ArrayList<>();  // No orders, so return empty list
        }
    }

    @Override
    public Optional<Order> getOrder(String date, int orderNumber) {
        var orders = getOrdersByDate(date);
        var matchingOrders = orders.stream().filter(o -> o.getOrderNumber() == orderNumber);
        return matchingOrders.findFirst();
    }

    @Override
    public void create(Order order) {
        var date = order.getDate();
        var orders = getOrdersByDate(date);

        // Make new order number
        var maxOrder = orders.stream().max((o1, o2) -> Integer.compare(o1.getOrderNumber(), o2.getOrderNumber()));
        if (maxOrder.isPresent()) {
            order.setOrderNumber(maxOrder.get().getOrderNumber() + 1);
        }
        else {
            order.setOrderNumber(1);
        }

        orders.add(order);

        try {
            writeOrdersByDate(orders, date);
        }
        catch (IOException e) {
            System.out.println("[DAO ERROR] Could not write orders for date " + date);
        }
    }

    @Override
    public void update(Order newOrder) {
        var date = newOrder.getDate();
        var orders = getOrdersByDate(date);

        var matchingOrders = orders.stream().filter(o -> o.getOrderNumber() == newOrder.getOrderNumber());
        var index = orders.indexOf(matchingOrders.findFirst().get());
        orders.set(index, newOrder);

        try {
            writeOrdersByDate(orders, date);
        }
        catch (IOException e) {
            System.out.println("[DAO ERROR] Could not write orders for date " + date);
        }
    }

    @Override
    public void delete(Order order) {
        var date = order.getDate();
        var orders = getOrdersByDate(date);

        var matchingOrders = orders.stream().filter(o -> o.getOrderNumber() == order.getOrderNumber());
        orders.remove(matchingOrders.findFirst().get());

        try {
            writeOrdersByDate(orders, date);
        }
        catch (IOException e) {
            System.out.println("[DAO ERROR] Could not write orders for date " + date);
        }
    }

}
