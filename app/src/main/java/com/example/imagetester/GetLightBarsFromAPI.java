package com.example.imagetester;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.os.Build;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Scanner;

public class GetLightBarsFromAPI {

    public static ArrayList<VehicleAccessory> myAccessoryList;

    Context context;


    public GetLightBarsFromAPI(Context context) {
        this.context = context;
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    public ArrayList<VehicleAccessory> getJSONLightBar() {

        String json = null;

        String accessoryType = "light_bars";

        try {
            URL myURL = new URL("https://openrpg.org/api/" + accessoryType + "/read.php");
            URLConnection myURLConnection = myURL.openConnection();
            myURLConnection.connect();

            InputStream response = myURLConnection.getInputStream();

            try (Scanner scanner = new Scanner(response)) {
                json = scanner.useDelimiter("\\A").next();
            }
        } catch (MalformedURLException e) {

        } catch (IOException e) {

            e.printStackTrace();
        }

        Gson gson = new Gson();

        LightBars myList = gson.fromJson(json, LightBars.class);

        System.out.println(json);

        myAccessoryList = new ArrayList<>();

        if (myList.light_bars != null) {
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
