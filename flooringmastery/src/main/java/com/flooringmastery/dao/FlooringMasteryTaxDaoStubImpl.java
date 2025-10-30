package com.flooringmastery.dao;

import java.math.BigDecimal;

public class FlooringMasteryTaxDaoStubImpl implements FlooringMasteryTaxDao {

    @Override
    public boolean exists(String stateAbbr) {
        return true;
    }

    @Override
    public BigDecimal getTaxRate(String stateAbbr) {
        return new BigDecimal("17.5");
    }

}
