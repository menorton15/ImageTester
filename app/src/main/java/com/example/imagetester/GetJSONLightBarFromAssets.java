package com.example.imagetester;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class GetJSONLightBarFromAssets {

    private static final String TAG = "GetLightBarsFromAssets";

    Context context;

    public GetJSONLightBarFromAssets(Context context) {
        this.context = context;
    }

    public ArrayList<VehicleAccessory> getJSONLightBar(String selectedVehicle) {
        String json = null;
        try {
            InputStream inputStream = context.getAssets().open("LightBarJSON.json");
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();
            json = new String(buffer, "UTF-8");
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        Log.i(TAG, "Getting light bars from the local JSON file.");

        Gson gson = new Gson();

        LightBars myList = gson.fromJson(json, LightBars.class);

        Log.i(TAG, "Checking for specs data: " + myList.light_bars.get(0).getSpecs());

        ArrayList<VehicleAccessory> myAccessoryList = new ArrayList<>();

        if(myList.light_bars != null) {
            for (int i = 0; i < myList.light_bars.size(); i++) {
                if(myList.light_bars.get(i).vehicleType.equals(selectedVehicle))
                    myAccessoryList.add(new VehicleAccessory(AccessoryType.LIGHTBAR,
                        myList.light_bars.get(i).partNumber,
                        myList.light_bars.get(i).name,
                        myList.light_bars.get(i).description,
                        myList.light_bars.get(i).brand,
                        myList.light_bars.get(i).manufacturerPartNumber,
                        myList.light_bars.get(i).price,
                        myList.light_bars.get(i).vehicleType,
                        myList.light_bars.get(i).getSpecs(),
                        myList.light_bars.get(i).image_url));
            }
        }
        return myAccessoryList;
    }
}
