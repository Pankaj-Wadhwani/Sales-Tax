package com.pankaj.io.SalesTax.dto;

import java.util.List;

public class Receipt {
    private List<Item> items;

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public Receipt(List<Item> items) {
        this.items = items;
    }
}
