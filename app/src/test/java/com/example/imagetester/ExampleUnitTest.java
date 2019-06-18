package com.example.imagetester;

import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.view.Display;

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

    public boolean isTouchNearBackWheel(int x, int y) {

        Point anchorPoint = new Point(485,700);

        int tireWidth = BitmapFactory.decodeResource(getResources(), R.drawable.jeep_tire).getWidth();
        int tireHeight = BitmapFactory.decodeResource(getResources(), R.drawable.jeep_tire).getHeight();

        int minX = (anchorPoint.x) * vehicleImage.getWidth() / 2400 ;
        int minY = (anchorPoint.y) * vehicleImage.getHeight() / 1260;
        int maxX = (anchorPoint.x + tireWidth) * vehicleImage.getWidth() / 2400 ;
        int maxY = (anchorPoint.y + tireHeight) * vehicleImage.getHeight() / 1260;

        if (x <= maxX && x >= minX && y <= maxY && y >= minY){
            return true;
        }
        return false;
    }
}