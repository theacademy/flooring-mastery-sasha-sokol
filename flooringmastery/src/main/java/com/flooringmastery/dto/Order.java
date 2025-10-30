package com.flooringmastery.dto;

import java.math.BigDecimal;

public class Order {

    String date;  // MMDDYYYY
    int orderNumber;
    String customerName;
    String stateAbbr;
    BigDecimal taxRate;
    Product product;
    BigDecimal area;
    BigDecimal costPerSqFt;
    BigDecimal laborCostPerSqFt;
    BigDecimal materialCost;
    BigDecimal laborCost;
    BigDecimal tax;
    BigDecimal total;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getStateAbbr() {
        return stateAbbr;
    }

    public void setStateAbbr(String stateAbbr) {
        this.stateAbbr = stateAbbr;
    }

    public BigDecimal getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(BigDecimal taxRate) {
        this.taxRate = taxRate;
    }

    public Product getProduct() {
        return product;
    }

    public void setProductType(Product product) {
        this.product = product;
    }

    public BigDecimal getArea() {
        return area;
    }

    public void setArea(BigDecimal area) {
        this.area = area;
    }

    public BigDecimal getCostPerSqFt() {
        return costPerSqFt;
    }

    public void setCostPerSqFt(BigDecimal costPerSqFt) {
        this.costPerSqFt = costPerSqFt;
    }

    public BigDecimal getLaborCostPerSqFt() {
        return laborCostPerSqFt;
    }

    public void setLaborCostPerSqFt(BigDecimal laborCostPerSqFt) {
        this.laborCostPerSqFt = laborCostPerSqFt;
    }

    public BigDecimal getMaterialCost() {
        return materialCost;
    }

    public void setMaterialCost(BigDecimal materialCost) {
        this.materialCost = materialCost;
    }

    public BigDecimal getLaborCost() {
        return laborCost;
    }

    public void setLaborCost(BigDecimal laborCost) {
        this.laborCost = laborCost;
    }

    public BigDecimal getTax() {
        return tax;
    }

    public void setTax(BigDecimal tax) {
        this.tax = tax;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((date == null) ? 0 : date.hashCode());
        result = prime * result + orderNumber;
        result = prime * result + ((customerName == null) ? 0 : customerName.hashCode());
        result = prime * result + ((stateAbbr == null) ? 0 : stateAbbr.hashCode());
        result = prime * result + ((taxRate == null) ? 0 : taxRate.hashCode());
        result = prime * result + ((product == null) ? 0 : product.hashCode());
        result = prime * result + ((area == null) ? 0 : area.hashCode());
        result = prime * result + ((costPerSqFt == null) ? 0 : costPerSqFt.hashCode());
        result = prime * result + ((laborCostPerSqFt == null) ? 0 : laborCostPerSqFt.hashCode());
        result = prime * result + ((materialCost == null) ? 0 : materialCost.hashCode());
        result = prime * result + ((laborCost == null) ? 0 : laborCost.hashCode());
        result = prime * result + ((tax == null) ? 0 : tax.hashCode());
        result = prime * result + ((total == null) ? 0 : total.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Order other = (Order) obj;
        if (date == null) {
            if (other.date != null)
                return false;
        } else if (!date.equals(other.date))
            return false;
        if (orderNumber != other.orderNumber)
            return false;
        if (customerName == null) {
            if (other.customerName != null)
                return false;
        } else if (!customerName.equals(other.customerName))
            return false;
        if (stateAbbr == null) {
            if (other.stateAbbr != null)
                return false;
        } else if (!stateAbbr.equals(other.stateAbbr))
            return false;
        if (taxRate == null) {
            if (other.taxRate != null)
                return false;
        } else if (!taxRate.equals(other.taxRate))
            return false;
        if (product == null) {
            if (other.product != null)
                return false;
        } else if (!product.equals(other.product))
            return false;
        if (area == null) {
            if (other.area != null)
                return false;
        } else if (!area.equals(other.area))
            return false;
        if (costPerSqFt == null) {
            if (other.costPerSqFt != null)
                return false;
        } else if (!costPerSqFt.equals(other.costPerSqFt))
            return false;
        if (laborCostPerSqFt == null) {
            if (other.laborCostPerSqFt != null)
                return false;
        } else if (!laborCostPerSqFt.equals(other.laborCostPerSqFt))
            return false;
        if (materialCost == null) {
            if (other.materialCost != null)
                return false;
        } else if (!materialCost.equals(other.materialCost))
            return false;
        if (laborCost == null) {
            if (other.laborCost != null)
                return false;
        } else if (!laborCost.equals(other.laborCost))
            return false;
        if (tax == null) {
            if (other.tax != null)
                return false;
        } else if (!tax.equals(other.tax))
            return false;
        if (total == null) {
            if (other.total != null)
                return false;
        } else if (!total.equals(other.total))
            return false;
        return true;
    }

}
