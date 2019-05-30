package com.example.imagetester;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Bitmap bigImage = BitmapFactory.decodeResource(getResources(), R.drawable.jeep_body);
        Bitmap smallImage = BitmapFactory.decodeResource(getResources(), R.drawable.jeep_tire);
        Bitmap mergedImages = createSingleImageFromMultipleImages(bigImage, smallImage, 485, 700);
        mergedImages = createSingleImageFromMultipleImages(mergedImages, smallImage, 1650, 700);

        ImageView img = findViewById(R.id.imageView5);

        img.setImageBitmap(mergedImages);

    }


    private Bitmap createSingleImageFromMultipleImages(Bitmap firstImage, Bitmap secondImage, int x, int y){

        Bitmap result = Bitmap.createBitmap(firstImage.getWidth(), firstImage.getHeight(), firstImage.getConfig());
        Canvas canvas = new Canvas(result);
        canvas.drawBitmap(firstImage, 0f, 0f, null);
        canvas.drawBitmap(secondImage, x, y, null);
        return result;
    }

    public void changeTire(View view) {
        Bitmap bigImage = BitmapFactory.decodeResource(getResources(), R.drawable.jeep_body);
        Bitmap smallImage = BitmapFactory.decodeResource(getResources(), R.drawable.jeep_tire_2);
        Bitmap mergedImages = createSingleImageFromMultipleImages(bigImage, smallImage, 485, 700);
        mergedImages = createSingleImageFromMultipleImages(mergedImages, smallImage, 1650, 700);

        ImageView img = findViewById(R.id.imageView5);

        img.setImageBitmap(mergedImages);
    }
}
