package com.example.imagetester;

import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class LightBarItem extends VehicleAccessory {

    @SerializedName("id")
    String partNumber;
    String name;
    String description;
    String brand;
    @SerializedName("part_number")
    String manufacturerPartNumber;
    String price;
    @SerializedName("type")
    String vehicleType;
    String image_url;

    String pattern;
    String weight;
    String output_watts;
    String amp_draw;
    String leds;
    String lumens;

    String specs;

    public LightBarItem(AccessoryType accessoryType, String internalPartNumber, String partName,
                     String partDescription, String partBrand, String manufacturerPartNumber,
                     String partPrice, String vehicleTypeThatPartFits, String partSpecs,
                     String partImageURL) {
        super(accessoryType, internalPartNumber, partName, partDescription, partBrand,
                manufacturerPartNumber, partPrice, vehicleTypeThatPartFits, partSpecs, partImageURL);

    }

    public String getSpecs() {
        specs = "Light Bar Specs: " +
                "\n Pattern: " + pattern +
                "\n Weight: " + weight +
                "\n Output Watts: " + output_watts +
                "\n Amp Draw: " + amp_draw +
                "\n LEDs: " + leds +
                "\n Lumens: " + lumens;
        return specs;
    }

    public void setSpecs(String specs) {
        this.specs = specs;
    }

    /*public ArrayList<LightBarItem> loadJSONLightBarFromAssets() {
        ArrayList<LightBarItem> myLightBarList = new ArrayList<>();
        String json = null;

        try {
            InputStream inputStream = MainActivity.getAssets();
        }
        catch (IOException e){
            e.printStackTrace();
        }
        return myLightBarList;
        }

        private void getJSONLightBarFromAssets() {
            String json = null;
            try {
                InputStream inputStream = getAssets().open("LightBarJSON.json");
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
            //LightBarItem myLightBarItem = gson.fromJson(json, LightBarItem.class);

            ListOfLightBarItem myList = gson.fromJson(json, ListOfLightBarItem.class);

            //System.out.println("this is the amp: " + myLightBarItem.amp_draw);

            System.out.println("this is the amp: " + myList.get(0).amp_draw);
        }
        */

    }
