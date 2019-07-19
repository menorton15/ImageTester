package com.example.imagetester;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class GetJSONLiftKitsFromAssets {

    private static final String TAG = "GetLiftKitsFromAssets";

    Context context;

    public GetJSONLiftKitsFromAssets(Context context) {
        this.context = context;
    }
    
    public ArrayList<VehicleAccessory> getJSONLiftKits() {
        String json = null;
        try {
            InputStream inputStream = context.getAssets().open("LiftKitJSON.json");
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();
            json = new String(buffer, "UTF-8");
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        Log.i(TAG, "Getting lift_kits from the local JSON file.");

        Gson gson = new Gson();

        LiftKits myList = gson.fromJson(json, LiftKits.class);

        Log.i(TAG, "Checking for specs data: " + myList.lift_kits.get(0).getSpecs());

        ArrayList<VehicleAccessory> myAccessoryList = new ArrayList<>();

        if(myList.lift_kits != null) {
            for (int i = 0; i < myList.lift_kits.size(); i++) {
                myAccessoryList.add(new VehicleAccessory(AccessoryType.LIGHTBAR,
                        myList.lift_kits.get(i).partNumber,
                        myList.lift_kits.get(i).name,
                        myList.lift_kits.get(i).description,
                        myList.lift_kits.get(i).brand,
                        myList.lift_kits.get(i).manufacturerPartNumber,
                        myList.lift_kits.get(i).price,
                        myList.lift_kits.get(i).vehicleType,
                        myList.lift_kits.get(i).getSpecs(),
                        myList.lift_kits.get(i).image_url));
            }
        }
        return myAccessoryList;
    }
}
