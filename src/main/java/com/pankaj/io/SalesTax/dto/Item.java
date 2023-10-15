package com.pankaj.io.SalesTax.dto;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;

public class Item {
    private String name;
    private BigDecimal price;
    private boolean isImported;
    private Category category;

    private static final String IMPORTED = "imported";

    public Item(String name, double price, Category category) {
        this.name = name;
        this.price = BigDecimal.valueOf(price);
        this.isImported = name.toLowerCase().contains(IMPORTED);
        this.category = category;
    }

    public BigDecimal calculateTax() {
        BigDecimal tax = BigDecimal.ZERO;
        if (isImported) {
            tax = tax.add(price.multiply(BigDecimal.valueOf(0.05)));
        }
        if (!isExempt()) {
            tax = tax.add(price.multiply(BigDecimal.valueOf(getCategoryTaxRate())));
        }
        return roundUpToNearest0_05(tax);
    }

    public BigDecimal totalPrice() {
        return price.add(calculateTax());
    }

    private BigDecimal roundUpToNearest0_05(BigDecimal value) {
        return value.multiply(BigDecimal.valueOf(20)).setScale(0, RoundingMode.UP)
                .divide(BigDecimal.valueOf(20), 2, RoundingMode.UP);
    }

    private boolean isExempt() {
        return category == Category.BOOK || category == Category.FOOD || category == Category.MEDICAL;
    }

    private double getCategoryTaxRate() {
        return getCategoryBasicTaxRate() + (isImported ? 0.05 : 0);
    }

    private double getCategoryBasicTaxRate() {
        Map<Category, Double> basicTaxRates = new HashMap<Category, Double>();
        basicTaxRates.put(Category.BOOK, 0.0);
        basicTaxRates.put(Category.FOOD, 0.0);
        basicTaxRates.put(Category.MEDICAL, 0.0);
        basicTaxRates.put(Category.OTHER, 0.1);
        return basicTaxRates.getOrDefault(category, 0.1);
    }

    @Override
    public String toString() {
        return name + ": " + totalPrice().setScale(2, RoundingMode.HALF_UP);
    }
}
