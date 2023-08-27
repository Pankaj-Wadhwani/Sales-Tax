package com.pankaj.io.SalesTax.service;

import com.pankaj.io.SalesTax.dto.Item;
import com.pankaj.io.SalesTax.dto.Receipt;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;
import com.example.dto.Category;
import com.example.services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class ReceiptServiceImpl implements ReceiptService {

    @Autowired
    private ItemService itemService;

    public BigDecimal calculateSalesTaxes(Receipt receipt) {
        BigDecimal totalTax = BigDecimal.ZERO;
        for (Item item : receipt.getItems()) {
            totalTax = totalTax.add(itemService.calculateTax(item));
        }
        return totalTax;
    }

    public BigDecimal calculateTotal(Receipt receipt) {
        BigDecimal total = BigDecimal.ZERO;
        for (Item item : receipt.getItems()) {
            total = total.add(itemService.calculateTotalPrice(item));
        }
        return total;
    }
}
