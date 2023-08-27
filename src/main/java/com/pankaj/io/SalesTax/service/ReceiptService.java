package com.pankaj.io.SalesTax.service;

import com.pankaj.io.SalesTax.dto.Receipt;

import java.math.BigDecimal;

public interface ReceiptService {
    BigDecimal calculateSalesTaxes(Receipt receipt);

    BigDecimal calculateTotal(Receipt receipt);
}
