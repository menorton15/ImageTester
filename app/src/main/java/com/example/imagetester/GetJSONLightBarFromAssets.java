package com.example.imagetester;

import android.content.Context;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class GetJSONLightBarFromAssets {
    Context context;

    public GetJSONLightBarFromAssets(Context context) {
        this.context = context;
    }

    public ArrayList<VehicleAccessory> getJSONLightBar() {
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

        //Toast.makeText(this, "Getting light bar info", Toast.LENGTH_SHORT);

        System.out.println(json + "This is from the local folder");

        Gson gson = new Gson();
        //LightBars myLightBars = gson.fromJson(json, LightBars.class);

        LightBars myList = gson.fromJson(json, LightBars.class);

        //System.out.println("this is the amp: " + myLightBars.amp_draw);

        System.out.println("this is the amp: " + myList.light_bars.get(0).amp_draw);

        System.out.println("specs:" + myList.light_bars.get(1).getSpecs());

        ArrayList<VehicleAccessory> myAccessoryList = new ArrayList<>();

        if(myList.light_bars != null) {
            for (int i = 0; i < myList.light_bars.size(); i++) {
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
