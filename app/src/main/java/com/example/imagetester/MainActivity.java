package com.example.imagetester;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.LightingColorFilter;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.BitmapDrawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.util.List;


public class MainActivity extends AppCompatActivity {
    //Comment from Wendy new
    //Comment from Josh

    //Marks Comment
    String test = "Mark Test";
    List<String> Test = null;

    ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Bitmap bigImage = BitmapFactory.decodeResource(getResources(), R.drawable.jeep_body);
        Bitmap smallImage = BitmapFactory.decodeResource(getResources(), R.drawable.jeep_tire);
        Bitmap mergedImages = createSingleImageFromMultipleImages(bigImage, smallImage, 485, 700);
        mergedImages = createSingleImageFromMultipleImages(mergedImages, smallImage, 1650, 700);

        img = findViewById(R.id.imageView5);
        img.setImageBitmap(mergedImages); //Mark Merge Conflict
        test = test + "Mark Test";

    }

    // Merge conflicts galore

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

    public void changePaint(View view){
        /*BitmapDrawable bd = (BitmapDrawable) img.getDrawable();
        Bitmap bm = bd.getBitmap();

        Paint paint = new Paint();
        ColorFilter filter = new PorterDuffColorFilter(ContextCompat.getColor(this, R.color.colorPrimaryDark), PorterDuff.Mode.SRC_IN);

        paint.setColorFilter(filter);

        Canvas canvas = new Canvas(bm);
        canvas.drawBitmap(bm,0,0,paint);
        img.setImageBitmap(bm);
        */

//        LightingColorFilter filter = new LightingColorFilter(Color.WHITE, Color.BLUE);
        ColorMatrix filter = new ColorMatrix();
        filter.setRotate(1, 90f);
        img.setColorFilter(new ColorMatrixColorFilter(filter));
    }
}
