package it.unipd.mtss.model;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class UserTest {
    User user;

    @Before
    public void before() { user = new User(1, true, "Mario", "Rossi", "via Luzzati"); }

    @Test
    public void testGetId() { assertEquals(1, user.getId()); }

    @Test
    public void testIsAMinor() { assertEquals(true, user.isAMinor()); }

    @Test(expected=IllegalArgumentException.class)
    public void testNullName() { user = new User(1, true, "", "Rossi", "via Luzzati"); }

    @Test(expected=IllegalArgumentException.class)
    public void testNullSurname() { user = new User(1, true, "Mario", "", "via Luzzati"); }

    @Test(expected=IllegalArgumentException.class)
    public void testNullAddress() { user = new User(1, true, "Mario", "Rossi", ""); }

    @Test(expected=IllegalArgumentException.class)
    public void testLongName() { user = new User(1, true, "MarioGianmarcoGiovanni", "Rossi", "via Luzzati"); }

    @Test(expected=IllegalArgumentException.class)
    public void testLongSurname() { user = new User(1, true, "Mario", "RossiBianchiPesce", "via Luzzati"); }
}
