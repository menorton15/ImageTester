package com.example.imagetester;

import android.content.Context;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class GetJSONTiresFromAssets {
    Context context;

    public GetJSONTiresFromAssets(Context context) {
        this.context = context;
    }

    public ArrayList<VehicleAccessory> getJSONTires() {
        String json = null;
        try {
            InputStream inputStream = context.getAssets().open("TiresJSON.json");
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(json + "This is from the local folder");

        Gson gson = new Gson();

        Tires myList = gson.fromJson(json, Tires.class);

        //System.out.println("this is the amp: " + myLightBars.amp_draw);

        System.out.println("this is the amp: " + myList.tires.get(0).cross_section);

        System.out.println("specs:" + myList.tires.get(1).getSpecs());

        ArrayList<VehicleAccessory> myAccessoryList = new ArrayList<>();

        if(myList.tires != null) {
            for (int i = 0; i < myList.tires.size(); i++) {
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

