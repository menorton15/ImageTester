package com.example.imagetester;


import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import com.google.gson.Gson;


public class MainActivity extends AppCompatActivity {

    ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Bitmap bigImage = BitmapFactory.decodeResource(getResources(), R.drawable.jeep_body);
        Bitmap smallImage = BitmapFactory.decodeResource(getResources(), R.drawable.jeep_tire);
        Log.i("Information: ","Width is " + Integer.toString(smallImage.getWidth()));
        Bitmap mergedImages = ImageMerger.mergeImages(bigImage, smallImage, 485, 700);
        mergedImages = ImageMerger.mergeImages(mergedImages, smallImage, 1650, 700);

        img = findViewById(R.id.imageView5);
        img.setOnTouchListener(handleTouch);
        img.setImageBitmap(mergedImages);
        /*
        SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        String cartJson = sharedPref.getString("CART", "");
        String currentVehicleJson = sharedPref.getString("CURRENT_TAB", "");
        String currentTabString = sharedPref.getString("CURRENT_VEHICLE", "");

        Gson gson = new Gson();
        myCartObject cart = gson.fromJson(cartJson, myCartObject.class);
        myVehicleObject currentVehicle = gson.fromJson(currentVehicleJson, myVehicleObject.class);
        */

    }
    /*
    protected void onPause() {


        Gson gson = new Gson();
        String cartJson = gson.toJson(myCartObject);
        String currentVehicleJson = gson.toJson(myVehicleObject);

        SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("CART", cartJson);
        editor.putString("CURRENT_VEHICLE", currentVehicleJson);
        editor.putString("CURRENT_TAB", currentTabString);

        editor.apply();

    }*/
    private Bitmap mergeImages(Bitmap firstImage, Bitmap secondImage, int x, int y){

        Bitmap result = Bitmap.createBitmap(firstImage.getWidth(), firstImage.getHeight(), firstImage.getConfig());
        Canvas canvas = new Canvas(result);
        int newX = x * firstImage.getWidth() / 2400;
        int newY = y * firstImage.getHeight() / 1260;
        Log.i("Information", "Canvas width is " + canvas.getWidth());
        Log.i("Information", "Canvas height is " + canvas.getHeight());
        canvas.drawBitmap(firstImage, 0f, 0f, null);
        canvas.drawBitmap(secondImage, newX, newY, null);
        return result;
    }

    public void changeTire(View view) {
        Bitmap bigImage = BitmapFactory.decodeResource(getResources(), R.drawable.jeep_body);
        Bitmap smallImage = BitmapFactory.decodeResource(getResources(), R.drawable.jeep_tire_2);
        Bitmap mergedImages = ImageMerger.mergeImages(bigImage, smallImage, 485, 700);
        mergedImages = ImageMerger.mergeImages(mergedImages, smallImage, 1650, 700);

        ImageView img = findViewById(R.id.imageView5);


        img.setImageBitmap(mergedImages);

        Button button = findViewById(R.id.button4);
        button.setBackgroundColor(Color.LTGRAY);


    }

    private View.OnTouchListener handleTouch = new View.OnTouchListener() {

        @Override
        public boolean onTouch(View view, MotionEvent event) {

            int x = (int) event.getX();
            int y = (int) event.getY();

            if (event.getAction() == MotionEvent.ACTION_DOWN){
                if (isTouchNearBackWheel(x,y)){
                    changeTire(img);
                }
            }

            return true;
        }
    };

    public boolean isTouchNearBackWheel(int x, int y) {

        Point anchorPoint = new Point(485,700);

        int tireWidth = BitmapFactory.decodeResource(getResources(), R.drawable.jeep_tire).getWidth();
        int tireHeight = BitmapFactory.decodeResource(getResources(), R.drawable.jeep_tire).getHeight();

        int minX = (anchorPoint.x) * img.getWidth() / 2400 ;
        int minY = (anchorPoint.y) * img.getHeight() / 1260;
        int maxX = (anchorPoint.x + tireWidth) * img.getWidth() / 2400 ;
        int maxY = (anchorPoint.y + tireHeight) * img.getHeight() / 1260;

        Display display = getWindowManager().getDefaultDisplay();

        Log.i("Display", "Display is " + display.getWidth() + "x" + display.getHeight());
        Log.i("Touch","X = " + x + ", Y = " + y);
        Log.i("Touch", "Min x is " + minX + "; Max x is " + maxX);
        Log.i("Touch", "Min y is " + minY + "; Max y is " + maxY);
        Log.i("Information: ", "Imageview width is " + img.getWidth());
        Log.i("Information: ", "Imageview height is " + img.getHeight());

        if (x <= maxX && x >= minX && y <= maxY && y >= minY){
            return true;
        }
        return false;
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
        BitmapDrawable bd = (BitmapDrawable) img.getDrawable();
        Bitmap image = bd.getBitmap();


        /****************************************************************************
         * This code is for changing the color, however it will need to be run on a
         * separate thread if used in the final project.
         * **************************************************************************
         */
        for (int x = 0; x < image.getWidth(); x++){
            for (int y = 0; y < image.getHeight(); y++){
                int pixel = image.getPixel(x,y);
                int red = Color.red(pixel);
                int blue = Color.blue(pixel);
                int green = Color.green(pixel);
                int color2 = Color.RED;


                // TODO: Add variables newBlue, and newGreen and set those variables based on the desired color.
                if (Math.abs (red - Color.red (color2)) > 10 && red > 50 && red < 250 ){
                    int newPixel = pixel;

                    int newRed = red - 150;
                    if (newRed > 255){
                        newRed = 255;
                    } else if (newRed < 0){
                        newRed = 0;
                    }

                    image.setPixel(x,y, Color.rgb(blue, green, red));
                }
            }
        }

        img.setImageBitmap(image);

    }
}