package com.example.imagetester;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class GetJSONWheelsFromAssets {

    private static final String TAG = "GetWheelsFromAssets";

    Context context;

    public GetJSONWheelsFromAssets(Context context) {
        this.context = context;
    }

    public ArrayList<VehicleAccessory> getJSONWheels() {
        String json = null;
        try {
            InputStream inputStream = context.getAssets().open("WheelsJSON.json");
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();
            json = new String(buffer, "UTF-8");
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        Log.i(TAG, "Getting wheels from the local JSON file.");

        Gson gson = new Gson();

        Wheels myList = gson.fromJson(json, Wheels.class);

        Log.i(TAG, "Checking for specs data: " + myList.wheels.get(0).getSpecs());

        ArrayList<VehicleAccessory> myAccessoryList = new ArrayList<>();

        if(myList.wheels != null) {
            for (int i = 0; i < myList.wheels.size(); i++) {
                myAccessoryList.add(new VehicleAccessory(AccessoryType.LIGHTBAR,
                        myList.wheels.get(i).partNumber,
                        myList.wheels.get(i).name,
                        myList.wheels.get(i).description,
                        myList.wheels.get(i).brand,
                        myList.wheels.get(i).manufacturerPartNumber,
                        myList.wheels.get(i).price,
                        myList.wheels.get(i).vehicleType,
                        myList.wheels.get(i).getSpecs(),
                        myList.wheels.get(i).image_url));
            }
        }
        return myAccessoryList;
    }
}
