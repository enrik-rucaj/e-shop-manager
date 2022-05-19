package it.unipd.mtss.model;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class EItemTest {
    EItem item;

    @Before
    public void before() { item = new EItem(EItem.type.Mouse, "Hp", 26.99); }

    @Test
    public void testGetItemType() {
        assertEquals(EItem.type.Mouse, item.getItemType());
    }

    @Test
    public void testGetName() {
        assertEquals("Hp", item.getName());
    }

    @Test
    public void testGetPrice() {
        assertEquals(26.99, item.getPrice(), 0.001);
    }

    @Test(expected=IllegalArgumentException.class)
    public void testPositivePrice() { item = new EItem(EItem.type.Mouse, "Hp", -1.2); }
}
