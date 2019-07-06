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

    public ImageFetch(String URL, ImageView view){
        tireImageUrl = URL;
        img = view;
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
        } catch (Exception e) {
            e.printStackTrace();
            Log.i("Database", e.toString());
        }
    }
}