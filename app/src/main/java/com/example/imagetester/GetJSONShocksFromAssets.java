package com.example.imagetester;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class GetJSONShocksFromAssets {

    private static final String TAG = "GetShocksFromAssets";

    Context context;

    public GetJSONShocksFromAssets(Context context) {
        this.context = context;
    }

    public ArrayList<VehicleAccessory> getJSONShocks() {
        String json = null;
        try {
            InputStream inputStream = context.getAssets().open("ShocksJSON.json");
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();
            json = new String(buffer, "UTF-8");
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        Log.i(TAG, "Getting shocks from the local JSON file.");

        Gson gson = new Gson();

        Shocks myList = gson.fromJson(json, Shocks.class);

        Log.i(TAG, "Checking for specs data: " + myList.shocks.get(0).getSpecs());

        ArrayList<VehicleAccessory> myAccessoryList = new ArrayList<>();

        if(myList.shocks != null) {
            for (int i = 0; i < myList.shocks.size(); i++) {
                myAccessoryList.add(new VehicleAccessory(AccessoryType.LIGHTBAR,
                        myList.shocks.get(i).partNumber,
                        myList.shocks.get(i).name,
                        myList.shocks.get(i).description,
                        myList.shocks.get(i).brand,
                        myList.shocks.get(i).manufacturerPartNumber,
                        myList.shocks.get(i).price,
                        myList.shocks.get(i).vehicleType,
                        myList.shocks.get(i).getSpecs(),
                        myList.shocks.get(i).image_url));
            }
        }
        return myAccessoryList;
    }
}
