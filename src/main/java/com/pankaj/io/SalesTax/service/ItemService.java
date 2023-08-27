package com.pankaj.io.SalesTax.service;

import com.pankaj.io.SalesTax.dto.Item;

import java.math.BigDecimal;

public interface ItemService {
    BigDecimal calculateTax(Item item);

    BigDecimal calculateTotalPrice(Item item);
}