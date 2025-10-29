package com.flooringmastery.dao;

import java.math.BigDecimal;

public interface FlooringMasteryTaxDao {

    public boolean exists(String stateAbbr);

    public BigDecimal getTaxRate(String stateAbbr);

}
