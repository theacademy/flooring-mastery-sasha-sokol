package com.flooringmastery.dao;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import org.springframework.stereotype.Component;

@Component
public final class FlooringMasteryTaxDaoFileImpl implements FlooringMasteryTaxDao {

    private static final String TAX_FILE = "sample/Data/Taxes.txt";
    private static final String DELIMITER = ",";

    private Map<String, BigDecimal> taxRates = new HashMap<>();

    public FlooringMasteryTaxDaoFileImpl() throws FileNotFoundException {
        loadData();
    }

    public void loadData() throws FileNotFoundException {
        var sc = new Scanner(new BufferedReader(new FileReader(TAX_FILE)));

        sc.nextLine();  // Skip header row

        while (sc.hasNextLine()) {
            unmarshallLine(sc.nextLine());
        }
    }

    private void unmarshallLine(String line) {
        String[] tokens = line.split(DELIMITER);
        taxRates.put(tokens[0], new BigDecimal(tokens[2]));
    }

    @Override
    public boolean exists(String stateAbbr) {
        return taxRates.containsKey(stateAbbr);
    }

    @Override
    public BigDecimal getTaxRate(String stateAbbr) {
        return taxRates.get(stateAbbr);
    }

}
