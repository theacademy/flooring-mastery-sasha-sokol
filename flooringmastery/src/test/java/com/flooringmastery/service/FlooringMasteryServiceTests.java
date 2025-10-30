package com.flooringmastery.service;

import java.math.BigDecimal;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.flooringmastery.dao.FlooringMasteryDao;
import com.flooringmastery.dao.FlooringMasteryProductsDao;
import com.flooringmastery.dao.FlooringMasteryProductsDaoStubImpl;
import com.flooringmastery.dao.FlooringMasteryTaxDao;
import com.flooringmastery.dao.FlooringMasteryTaxDaoStubImpl;
import com.flooringmastery.dto.Order;

@SpringBootTest
public class FlooringMasteryServiceTests {

    FlooringMasteryServiceImpl service;

    FlooringMasteryDao dao = null;

    FlooringMasteryProductsDao productsDao = new FlooringMasteryProductsDaoStubImpl();

    FlooringMasteryTaxDao taxDao = new FlooringMasteryTaxDaoStubImpl();

    private Order exampleOrder() {
        Order o = new Order();
        o.setDate("12252025");
        o.setOrderNumber(1);
        o.setCustomerName("Jane Doe");
        o.setStateAbbr("KY");
        o.setProductType(productsDao.getProduct("Tile"));
        o.setArea(new BigDecimal("100.5"));
        return o;
    }

    public FlooringMasteryServiceTests() {
        AnnotationConfigApplicationContext appContext = new AnnotationConfigApplicationContext();
		appContext.scan("com.flooringmastery");
		appContext.refresh();
        service = appContext.getBean("flooringMasteryServiceImpl", FlooringMasteryServiceImpl.class);
    }

    @BeforeAll
    public static void setUpClass() {
    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() {
        service = new FlooringMasteryServiceImpl(dao, productsDao, taxDao);
    }

    @AfterEach
    public void tearDown() {
    }

    @Test
    public void testCalculate() {
        Order o1 = exampleOrder(), o2 = exampleOrder();
        service.calculateMissingFields(o2);

        assertEquals(o1.getDate(), o2.getDate());
        assertEquals(o1.getOrderNumber(), o2.getOrderNumber());
        assertEquals(o1.getCustomerName(), o2.getCustomerName());
        assertEquals(o1.getStateAbbr(), o2.getStateAbbr());
        assertEquals(o1.getProduct(), o2.getProduct());
        assertEquals(o1.getArea(), o2.getArea());

        assertEquals(taxDao.getTaxRate(o1.getStateAbbr()), o2.getTaxRate());
        assertEquals(o1.getProduct().getCostPerSquareFoot(), o2.getCostPerSqFt());
        assertEquals(o1.getProduct().getLaborCostPerSquareFoot(), o2.getLaborCostPerSqFt());
        
        assertEquals(o2.getArea().multiply(o2.getCostPerSqFt()), o2.getMaterialCost());
        assertEquals(o2.getArea().multiply(o2.getLaborCostPerSqFt()), o2.getLaborCost());
        assertEquals(o2.getMaterialCost().add(o2.getLaborCost()).multiply(o2.getTaxRate().divide(new BigDecimal(100))), o2.getTax());
        assertEquals(o2.getMaterialCost().add(o2.getLaborCost()).add(o2.getTax()), o2.getTotal());
    }

}
