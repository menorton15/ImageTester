package com.example.imagetester;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.android.volley.Response;

import org.json.JSONObject;

import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ImageFetch extends AsyncTask<VehicleAccessory,Void, ArrayList<VehicleAccessory>> /*implements AccessoryListRecyclerViewAdapter.OnItemClickListener*/{

    //String tireImageUrl;
    //ImageView img;
    //VehicleAccessory accessory;
    WeakReference<Activity> activity;
    AccessoryListRecyclerViewAdapter myAccessoryListRecyclerViewAdapter;
    RecyclerView myAccessoryListRecyclerView;
    Response.Listener<JSONObject> listener;
    ArrayList<VehicleAccessory> list;

    public ImageFetch(Activity a, AccessoryListRecyclerViewAdapter va, RecyclerView rv, Response.Listener<JSONObject> l){
        activity = new WeakReference<>(a);
        myAccessoryListRecyclerViewAdapter = va;
        myAccessoryListRecyclerView = rv;
        listener = l;
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
        list = new ArrayList<VehicleAccessory>(Arrays.asList(parts));
        return list;
    }

    protected void onPostExecute(ArrayList<VehicleAccessory> list) {
        myAccessoryListRecyclerViewAdapter =
                new AccessoryListRecyclerViewAdapter(activity.get().getApplicationContext(), list);
        myAccessoryListRecyclerView.setAdapter(myAccessoryListRecyclerViewAdapter);
        //myAccessoryListRecyclerViewAdapter.setOnItemClickListener(listener);
    }
/*
    @Override
    public void onAddToCartIconClick(int position) {

        VehicleAccessory clickedItem = myAccessoryList.get(position);

        int cartPosition = myCartList.size();

        myCartList.add(cartPosition, clickedItem);
        calculateTotalPrice();
        myCartListAdapter.notifyItemInserted(cartPosition);
    }*/
}