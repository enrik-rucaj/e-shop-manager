package it.unipd.mtss.business;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalTime;
import it.unipd.mtss.model.EItem;
import it.unipd.mtss.model.User;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class GiveAwayTest {
    GiveAway gift1, gift2, gift3;
    List<EItem> itemsOrdered;
    User user1, user2;
    LocalTime  orario;

    @Before
    public void before() {
        itemsOrdered = new ArrayList<EItem>();
        itemsOrdered.add(new EItem(EItem.type.Processor, "Intel i5 12Gen", 300.0));
        itemsOrdered.add(new EItem(EItem.type.Motherboard, "Asus Z390", 199.99));
        itemsOrdered.add(new EItem(EItem.type.Keyboard, "Corsair", 97.50));
        itemsOrdered.add(new EItem(EItem.type.Keyboard, "Corsair", 97.50));
        itemsOrdered.add(new EItem(EItem.type.Mouse, "Hp", 26.0));
        user1 = new User(1, false, "Mario", "Rossi", "via Luzzati");
        user2 = new User(2, true, "Giovanni", "Bianchi", "via Luzzati");
        orario = LocalTime.of(18, 43, 12);
        gift1 = new GiveAway(itemsOrdered, user1, orario);
        gift2 = new GiveAway(itemsOrdered, user2, orario);
        gift3 = new GiveAway(itemsOrdered, user2, LocalTime.of(14, 20, 30));
    }

    @Test
    public void testCanGiveAway() {
        assertTrue(gift2.canGiveAway());
        assertFalse(gift1.canGiveAway());
        assertFalse(gift3.canGiveAway());
    }

    @Test
    public void testGiveAwayAFreeItem(){
        assertTrue(gift2.GiveAwayAFreeItem());
    }

    @Test
    public void testGiveAwayAFreeItem_SameUserSecondTime(){
        assertTrue(gift2.GiveAwayAFreeItem());
        assertFalse(gift2.GiveAwayAFreeItem());
    }

    @Test
    public void testGiveAwayAFreeItem_MoreThan10Times(){
        for (int i=0; i<10; i++){
            user2 = new User(i, true, ("Giovanni"+i), "Bianchi", "via Luzzati");
            gift2 = new GiveAway(itemsOrdered, user2, orario);
            gift2.GiveAwayAFreeItem();
        }
        user2 = new User(20, true, ("Giovanni20"), "Bianchi", "via Luzzati");
        gift2 = new GiveAway(itemsOrdered, user2, orario);

        assertFalse(gift2.GiveAwayAFreeItem());
    }
}