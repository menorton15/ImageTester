package com.example.imagetester;

import android.graphics.Point;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void cartShouldNotBeEmpty() {
        assertNotEquals(null, cartObject);
        assertNotEquals(0.00, cartPriceTotal, 2);
    }

    @Test
    public void cartShouldBeEmpty() {
        assertEquals(null, cartObject);
        assertEquals(0.00, cartPriceTotal, 0);
    }

    @Test
    public void touchNearBackWheel() {
        assertTrue(isTouchNearBackWheel(touchPoint.getPoint()));
    }

    public void isTouchNearBackWheel(Point A) {
        int anchorX = backWheel.anchor().getX();
        int anchorY = backWheel.anchor().getY();
        int distanceX = tireWidth;
        int distanceY = tireHeight;


    }
}