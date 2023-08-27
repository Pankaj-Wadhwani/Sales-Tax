package com.pankaj.io.SalesTax;
import com.pankaj.io.SalesTax.dto.Category;
import com.pankaj.io.SalesTax.dto.Item;
import com.pankaj.io.SalesTax.service.ItemService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import com.example.dto.Item;
import com.example.dto.Receipt;
import com.example.dto.Category;
import com.example.services.ItemService;
import com.example.services.ReceiptService;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
public class ItemServiceTest {

    @Autowired
    private ItemService itemService;

    @Test
    public void testCalculateTax() {
        Item item = new Item("1 imported bottle of perfume", 27.99, Category.OTHER);
        BigDecimal expectedTax = BigDecimal.valueOf(4.20);
        assertEquals(expectedTax, itemService.calculateTax(item));
    }

    @Test
    public void testCalculateTotalPrice() {
        Item item = new Item("1 imported bottle of perfume", 27.99, Category.OTHER);
        BigDecimal expectedTotalPrice = BigDecimal.valueOf(32.19);
        assertEquals(expectedTotalPrice, itemService.calculateTotalPrice(item));
    }

    // ... Other test cases
}

