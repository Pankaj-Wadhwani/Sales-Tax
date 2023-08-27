package com.pankaj.io.SalesTax.controller;

import com.pankaj.io.SalesTax.dto.Item;
import com.pankaj.io.SalesTax.dto.Receipt;
import com.pankaj.io.SalesTax.service.ReceiptService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.ResponseEntity;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import com.example.services.ReceiptService;
import org.springframework.beans.factory.annotation.Autowired;

@Controller
public class ReceiptController {

    @Autowired
    private ReceiptService receiptService;

    @PostMapping("test.com/generate-receipt")
    public ResponseEntity<String> generateReceipt(@RequestBody Receipt receipt) {
        BigDecimal salesTaxes = receiptService.calculateSalesTaxes(receipt);
        BigDecimal total = receiptService.calculateTotal(receipt);
        String receiptDetails = getReceiptDetails(receipt, salesTaxes, total);
        return ResponseEntity.ok(receiptDetails);
    }

    private String getReceiptDetails(Receipt Receipt, BigDecimal salesTaxes, BigDecimal total) {
        StringBuilder result = new StringBuilder();
        for (Item item : Receipt.getItems()) {
            result.append(item.getName()).append(": ").append(item.getPrice()).append("\n");
        }
        result.append("Sales Taxes: ").append(salesTaxes.setScale(2, RoundingMode.HALF_UP)).append("\n");
        result.append("Total: ").append(total.setScale(2, RoundingMode.HALF_UP));
        return result.toString();
    }
}
