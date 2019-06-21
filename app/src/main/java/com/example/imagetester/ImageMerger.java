package com.example.imagetester;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.util.Log;

public class ImageMerger {
    static Bitmap mergeImages(Bitmap mainImage, Bitmap accessoryImage, int x, int y){

        Bitmap result = Bitmap.createBitmap(mainImage.getWidth(), mainImage.getHeight(), mainImage.getConfig());
        Canvas canvas = new Canvas(result);
        int newX = x * mainImage.getWidth() / 2400;
        int newY = y * mainImage.getHeight() / 1260;
        Log.i("Information", "Canvas width is " + canvas.getWidth());
        Log.i("Information", "Canvas height is " + canvas.getHeight());
        canvas.drawBitmap(mainImage, 0f, 0f, null);
        canvas.drawBitmap(accessoryImage, newX, newY, null);
        return result;
    }
}
