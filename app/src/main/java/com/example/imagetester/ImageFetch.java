package com.example.imagetester;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.NetworkOnMainThreadException;
import android.provider.ContactsContract;
import android.util.Log;
import android.widget.ImageView;

import java.io.InputStream;
import java.net.URL;

public class ImageFetch implements Runnable{

    String tireImageUrl;
    ImageView img;
    VehicleAccessory accessory;

    public ImageFetch(String URL, ImageView view, VehicleAccessory part){
        tireImageUrl = URL;
        img = view;
        accessory = part;
    }
    @Override
    public void run() {
        try {
            InputStream is = (InputStream) new URL(tireImageUrl).getContent();
            Bitmap d = Bitmap.createScaledBitmap(BitmapFactory.decodeStream(is), 368, 368, false);
            Log.i("Database", "Width: " + d.getWidth() + " Height: " + d.getHeight());
            BitmapDrawable bd = (BitmapDrawable) img.getDrawable();
            Bitmap main = bd.getBitmap();
            main = ImageMerger.mergeImages(main, d, 485, 700);
            main = ImageMerger.mergeImages(main, d, 1650, 700);
            img.setImageBitmap(main);
            accessory.setPartImage(d);
            Log.i("Image", "Image has been loaded");
        } catch (Exception e) {
            e.printStackTrace();
            Log.i("Database", e.toString());
        }
    }
}