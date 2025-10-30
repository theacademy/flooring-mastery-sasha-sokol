package com.flooringmastery.dao;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.math.BigDecimal;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.flooringmastery.dto.Order;

@SpringBootTest
public class FlooringMasteryDAOTests {

    private static final String TEST_DIR = "test/Orders/";

    private static final String TEST_DATE = "12252025";

    private static String fileName(String date) {
        return "Orders_" + date + ".txt";
    }

    private static final String HEADER = "OrderNumber,CustomerName,State,TaxRate,ProductType,Area,CostPerSquareFoot,LaborCostPerSquareFoot,MaterialCost,LaborCost,Tax,Total";

    FlooringMasteryDaoFileImpl dao;

    FlooringMasteryProductsDao productsDao = new FlooringMasteryProductsDaoStubImpl();

    private Order exampleOrder() {
        Order o = new Order();
        o.setDate(TEST_DATE);
        o.setOrderNumber(1);
        o.setCustomerName("Jane Doe");
        o.setStateAbbr("KY");
        o.setTaxRate(new BigDecimal(17.5));
        o.setProductType(productsDao.getProduct("Tile"));
        o.setArea(new BigDecimal("100.5"));
        o.setCostPerSqFt(new BigDecimal("5"));
        o.setLaborCostPerSqFt(new BigDecimal("4.15"));
        o.setMaterialCost(new BigDecimal(55.5));
        o.setLaborCost(new BigDecimal(55.5));
        o.setTax(new BigDecimal(7.5));
        o.setTotal(new BigDecimal(150.5));
        return o;
    }

    public FlooringMasteryDAOTests() {
        AnnotationConfigApplicationContext appContext = new AnnotationConfigApplicationContext();
		appContext.scan("com.flooringmastery");
		appContext.refresh();
        dao = appContext.getBean("flooringMasteryDaoFileImpl", FlooringMasteryDaoFileImpl.class);
    }

    @BeforeAll
    public static void setUpClass() {
    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() throws Exception {
        var out = new PrintWriter(new FileWriter(TEST_DIR + fileName(TEST_DATE)));
        out.println(HEADER);
        out.close();

        dao = new FlooringMasteryDaoFileImpl(TEST_DIR, productsDao);
    }

    @AfterEach
    public void tearDown() {
    }

    @Test
    public void testCreate() {
        Order order = exampleOrder();
        dao.create(order);

        var response = dao.getOrder(order.getDate(), order.getOrderNumber());

        assertTrue(response.isPresent());
        assertEquals(order, response.get());
    }

    @Test
    public void testUpdate() {
        Order order = exampleOrder();
        dao.create(order);
        
        Order newOrder = exampleOrder();
        newOrder.setCustomerName("Jane Doe Inc.");
        dao.update(newOrder);

        var response = dao.getOrder(order.getDate(), order.getOrderNumber());
        
        assertTrue(response.isPresent());
        assertEquals(newOrder, response.get());
    }

    @Test
    public void testRemove() {
        Order order = exampleOrder();
        dao.create(order);

        dao.delete(exampleOrder());

        var response = dao.getOrder(order.getDate(), order.getOrderNumber());

        assertFalse(response.isPresent());
    }

}
