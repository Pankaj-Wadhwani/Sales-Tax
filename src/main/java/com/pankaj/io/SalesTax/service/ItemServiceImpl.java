package com.pankaj.io.SalesTax.service;

import com.pankaj.io.SalesTax.dto.Category;
import com.pankaj.io.SalesTax.dto.Item;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;
import com.example.dto.Category;
import com.example.services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;


@Service
public class ItemServiceImpl implements ItemService {

    public BigDecimal calculateTax(Item item) {
        BigDecimal tax = BigDecimal.ZERO;
        if (item.getName().toLowerCase().contains("imported")) {
            tax = tax.add(BigDecimal.valueOf(item.getPrice()).multiply(BigDecimal.valueOf(0.05)));
        }
        if (!isExempt(item.getCategory())) {
            tax = tax.add(BigDecimal.valueOf(item.getPrice()).multiply(BigDecimal.valueOf(getCategoryTaxRate(item.getCategory()))));
        }
        return roundUpToNearest0_05(tax);
    }

    public BigDecimal calculateTotalPrice(Item item) {
        return BigDecimal.valueOf(item.getPrice()).add(calculateTax(item));
    }

    private BigDecimal roundUpToNearest0_05(BigDecimal value) {
        return value.multiply(BigDecimal.valueOf(20)).setScale(0, RoundingMode.UP)
                .divide(BigDecimal.valueOf(20), 2, RoundingMode.UP);
    }

    private boolean isExempt(Category category) {
        return category == Category.BOOK || category == Category.FOOD || category == Category.MEDICAL;
    }

}

