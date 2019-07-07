package com.example.imagetester;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Response;

import org.json.JSONObject;

import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.lang.Float.valueOf;

public class ImageFetch extends AsyncTask<VehicleAccessory,Void, ArrayList<VehicleAccessory>> implements AccessoryListRecyclerViewAdapter.OnItemClickListener{

    //String tireImageUrl;
    //ImageView img;
    //VehicleAccessory accessory;
    WeakReference<Activity> activity;
    CartListRecyclerViewAdapter myCartListAdapter;
    AccessoryListRecyclerViewAdapter myAccessoryListRecyclerViewAdapter;
    RecyclerView myAccessoryListRecyclerView;
    Response.Listener<JSONObject> listener;
    ArrayList<VehicleAccessory> list;
    ArrayList<VehicleAccessory> myCartList;
    TextView totalPriceTextView;
    ImageView img;

    public ImageFetch(Activity a, CartListRecyclerViewAdapter ca, AccessoryListRecyclerViewAdapter va, RecyclerView rv, ArrayList<VehicleAccessory> cartList, TextView v, ImageView image){
        activity = new WeakReference<>(a);
        myCartListAdapter = ca;
        myAccessoryListRecyclerViewAdapter = va;
        myAccessoryListRecyclerView = rv;
        totalPriceTextView = v;
        myCartList = cartList;
        img = image;
    }
    /*public ImageFetch(Activity a, String URL, ImageView view, VehicleAccessory part){
        tireImageUrl = URL;
        img = view;
        accessory = part;
        activity = new WeakReference<>(a);
    }*/

    /*@Override
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

        activity.get().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                test.
            }
        });
    }*/


    @Override
    protected ArrayList<VehicleAccessory> doInBackground(VehicleAccessory[] parts) {

        for (VehicleAccessory part : parts) {
            try {
                InputStream is = (InputStream) new URL(part.getPartImageURL()).getContent();
                Bitmap d = Bitmap.createScaledBitmap(BitmapFactory.decodeStream(is), 368, 368, false);
                //Log.i("Database", "Width: " + d.getWidth() + " Height: " + d.getHeight());
                //BitmapDrawable bd = (BitmapDrawable) img.getDrawable();
                //Bitmap main = bd.getBitmap();
                //main = ImageMerger.mergeImages(main, d, 485, 700);
                //main = ImageMerger.mergeImages(main, d, 1650, 700);
                //img.setImageBitmap(main);
                //accessory.setPartImage(d);
                //Log.i("Image", "Image has been loaded");
                part.setPartImage(d);
            } catch (Exception e) {
                e.printStackTrace();
                Log.i("Database", e.toString());
            }
        }
        list = new ArrayList<>(Arrays.asList(parts));
        return list;
    }

    protected void onPostExecute(ArrayList<VehicleAccessory> list) {
        myAccessoryListRecyclerViewAdapter =
                new AccessoryListRecyclerViewAdapter(activity.get().getApplicationContext(), list);
        myAccessoryListRecyclerView.setAdapter(myAccessoryListRecyclerViewAdapter);
        myAccessoryListRecyclerViewAdapter.setOnItemClickListener(this);
    }

    @Override
    public void onAddToCartIconClick(int position) {

        VehicleAccessory clickedItem = list.get(position);

        int cartPosition = myCartList.size();

        myCartList.add(cartPosition, clickedItem);
        BitmapDrawable bd = (BitmapDrawable) img.getDrawable();
        Bitmap main = bd.getBitmap();
        Bitmap d = clickedItem.getPartImage();
        main = ImageMerger.mergeImages(main, d, 485, 700);
        main = ImageMerger.mergeImages(main, d, 1650, 700);
        img.setImageBitmap(main);
        //accessory.setPartImage(d);
        calculateTotalPrice();
        myCartListAdapter.notifyItemInserted(cartPosition);
    }

    public void calculateTotalPrice() {
        Float total = 0f;

        for (int i = 0; i < myCartList.size(); i++) {
            total += valueOf(myCartList.get(i).getPartPrice());
        }


        totalPriceTextView.setText("Total: $ " + String.format("%.2f", total));
    }
}