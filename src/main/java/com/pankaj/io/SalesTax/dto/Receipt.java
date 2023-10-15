package com.pankaj.io.SalesTax.dto;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class Receipt {
    private List<Item> items = new ArrayList<Item>();

    public void addItem(Item item) {
        items.add(item);
    }

    public BigDecimal calculateSalesTaxes() {
        BigDecimal totalTax = BigDecimal.ZERO;
        for (Item item : items) {
            totalTax = totalTax.add(item.calculateTax());
        }
        return totalTax;
    }

    public BigDecimal calculateTotal() {
        BigDecimal total = BigDecimal.ZERO;
        for (Item item : items) {
            total = total.add(item.totalPrice());
        }
        return total;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (Item item : items) {
            result.append(item).append("\n");
        }
        BigDecimal salesTaxes = calculateSalesTaxes();
        BigDecimal total = calculateTotal();
        result.append("Sales Taxes: ").append(salesTaxes.setScale(2, RoundingMode.HALF_UP)).append("\n");
        result.append("Total: ").append(total.setScale(2, RoundingMode.HALF_UP));
        return result.toString();
    }
}
