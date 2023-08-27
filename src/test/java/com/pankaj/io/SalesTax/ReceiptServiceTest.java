package com.pankaj.io.SalesTax;
import com.pankaj.io.SalesTax.dto.Category;
import com.pankaj.io.SalesTax.dto.Item;
import com.pankaj.io.SalesTax.dto.Receipt;
import com.pankaj.io.SalesTax.service.ReceiptService;
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
public class ReceiptServiceTest {

    @Autowired
    private ReceiptService receiptService;

    @Test
    public void testCalculateSalesTaxes() {
        List<Item> items = new ArrayList<>();
        items.add(new Item("1 book", 12.49, Category.BOOK));
        items.add(new Item("1 imported music CD", 14.99, Category.OTHER));
        items.add(new Item("1 chocolate bar", 0.85, Category.FOOD));
        Receipt receipt = new Receipt(items);
        BigDecimal expectedSalesTaxes = BigDecimal.valueOf(1.50);
        assertEquals(expectedSalesTaxes, receiptService.calculateSalesTaxes(receipt));
    }

    @Test
    public void testCalculateTotal() {
        List<Item> items = new ArrayList();
        items.add(new Item("1 book", 12.49, Category.BOOK));
        items.add(new Item("1 imported music CD", 14.99, Category.OTHER));
        items.add(new Item("1 chocolate bar", 0.85, Category.FOOD));
        Receipt receipt = new Receipt(items);
        BigDecimal expectedTotal = BigDecimal.valueOf(29.83);
        assertEquals(expectedTotal, receiptService.calculateTotal(receipt));
    }

    // ... Other test cases
}

