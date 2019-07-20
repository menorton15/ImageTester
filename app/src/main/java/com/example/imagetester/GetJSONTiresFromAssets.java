package com.example.imagetester;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class GetJSONTiresFromAssets {

    private static final String TAG = "GetTiresFromAssets";

    Context context;

    public GetJSONTiresFromAssets(Context context) {
        this.context = context;
    }

    public ArrayList<VehicleAccessory> getJSONTires(String selectedVehicle) {
        String json = null;
        try {
            InputStream inputStream = context.getAssets().open("TiresJSON.json");
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();
            json = new String(buffer, "UTF-8");
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        Log.i(TAG, "Getting tires from the local JSON file.");

        Gson gson = new Gson();

        Tires myList = gson.fromJson(json, Tires.class);

        Log.i(TAG, "Checking for specs data: " + myList.tires.get(0).getSpecs());

        ArrayList<VehicleAccessory> myAccessoryList = new ArrayList<>();

        if(myList.tires != null) {
            for (int i = 0; i < myList.tires.size(); i++) {
                if(myList.tires.get(i).vehicleType.equals(selectedVehicle))
                    myAccessoryList.add(new VehicleAccessory(AccessoryType.TIRES,
                        myList.tires.get(i).partNumber,
                        myList.tires.get(i).name,
                        myList.tires.get(i).description,
                        myList.tires.get(i).brand,
                        myList.tires.get(i).manufacturerPartNumber,
                        myList.tires.get(i).price,
                        myList.tires.get(i).vehicleType,
                        myList.tires.get(i).getSpecs(),
                        myList.tires.get(i).image_url));
            }
        }
        return myAccessoryList;
    }
}

