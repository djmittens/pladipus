package me.ngrid.misc;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 */
public class BusRoutesTest {
    private BusRoutes app;

    @Before
    public void setUp () {
        app = new BusRoutes();
    }

    @Test
    public void smallTest() {
        app.addRider(1, 5);
        app.addRider(2, 4);
        app.addRider(3, 4);
        app.addRider(4, 5);
        app.setBusCapacity(2);
        assertEquals(2, app.getMinBusses());
    }

    @Test
    public void largeTest() {
        app.addRider(0, 10);
        app.addRider(2, 4);
        app.addRider(3, 4);
        app.addRider(4, 5);
        app.addRider(4, 5);
        app.addRider(4, 5);
        app.addRider(4, 5);
        app.addRider(4, 5);

        app.setBusCapacity(2);
        assertEquals(3, app.getMinBusses());
    }

    @Test
    public void largeSingleBusTest() {
        app.addRider(0, 10);
        app.addRider(0, 2);
        app.addRider(0, 3);
        app.addRider(2, 4);
        app.addRider(3, 5);
        app.addRider(4, 6);
        app.addRider(5, 7);
        app.addRider(6, 8);
        app.addRider(7, 9);
        app.addRider(8, 10);

        app.setBusCapacity(3);
        assertEquals(1, app.getMinBusses());
    }
}