package it.unipd.mtss.business;

import it.unipd.mtss.business.exception.BillException;
import it.unipd.mtss.model.EItem;
import it.unipd.mtss.model.User;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalTime;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class BillImplTest {
    BillImpl bill;
    List<EItem> itemsOrdered;
    User user;
    LocalTime orario;

    @Before
    public void before() {
        bill = new BillImpl();
        itemsOrdered = new ArrayList<EItem>();
        user = new User(1, false, "Mario", "Rossi", "via Luzzati");
        orario = LocalTime.of(18,43,12);
    }

    @Test
    public void testGetSum() throws BillException {
        itemsOrdered.add(new EItem(EItem.type.Processor, "Intel i5 12Gen", 300.0));
        itemsOrdered.add(new EItem(EItem.type.Motherboard, "Asus Z390", 199.99));
        itemsOrdered.add(new EItem(EItem.type.Keyboard, "Corsair", 97.50));
        itemsOrdered.add(new EItem(EItem.type.Mouse, "Hp", 26.0));

        assertEquals(623.49, bill.getOrderPrice(itemsOrdered, user, orario), 0.001);
    }

    @Test(expected=BillException.class)
    public void testGetSumWithEmptyList() throws BillException {
        bill.getOrderPrice(itemsOrdered, user, orario);
    }

    @Test(expected=BillException.class)
    public void testGetSumWithNullList() throws BillException {
        itemsOrdered = null;

        bill.getOrderPrice(itemsOrdered, user, orario);
    }

    @Test(expected=BillException.class)
    public void testGetSumWithNullUser() throws BillException {
        user = null;

        itemsOrdered.add(new EItem(EItem.type.Mouse, "Hp", 26.0));

        bill.getOrderPrice(itemsOrdered, user, orario);
    }

    @Test
    public void testDiscountOnProcessorAfterFiveBought() throws BillException {
        itemsOrdered.add(new EItem(EItem.type.Processor, "Intel i5 12Gen", 170.0));
        itemsOrdered.add(new EItem(EItem.type.Processor, "Intel i5 12Gen", 170.0));
        itemsOrdered.add(new EItem(EItem.type.Processor, "Intel i5 12Gen", 170.0));
        itemsOrdered.add(new EItem(EItem.type.Processor, "Intel i5 12Gen", 170.0));
        itemsOrdered.add(new EItem(EItem.type.Processor, "Intel i5 12Gen", 170.0));
        itemsOrdered.add(new EItem(EItem.type.Processor, "Intel i3 5Gen", 80.50));

        assertEquals(890.25, bill.getOrderPrice(itemsOrdered, user, orario), 0.001);
    }

    @Test
    public void testGiveAwayLessExpensiveMouseAfterTenBought() throws BillException {
        itemsOrdered.add(new EItem(EItem.type.Mouse, "Logitech", 80.0));
        itemsOrdered.add(new EItem(EItem.type.Mouse, "Logitech", 80.0));
        itemsOrdered.add(new EItem(EItem.type.Mouse, "Logitech", 80.0));
        itemsOrdered.add(new EItem(EItem.type.Mouse, "Logitech", 80.0));
        itemsOrdered.add(new EItem(EItem.type.Mouse, "Logitech", 80.0));
        itemsOrdered.add(new EItem(EItem.type.Mouse, "Logitech", 80.0));
        itemsOrdered.add(new EItem(EItem.type.Mouse, "Logitech", 80.0));
        itemsOrdered.add(new EItem(EItem.type.Mouse, "Logitech", 80.0));
        itemsOrdered.add(new EItem(EItem.type.Mouse, "Logitech", 80.0));
        itemsOrdered.add(new EItem(EItem.type.Mouse, "Hp", 26.99));
        itemsOrdered.add(new EItem(EItem.type.Mouse, "Logitech", 80.0));

        assertEquals(800.0, bill.getOrderPrice(itemsOrdered, user, orario), 0.001);
    }
}
