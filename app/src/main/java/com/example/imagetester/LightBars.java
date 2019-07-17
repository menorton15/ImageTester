package com.example.imagetester;

import com.google.gson.annotations.SerializedName;

public class LightBars extends VehicleAccessory
{

    @SerializedName("id")
    String partNumber;
    String name;
    String description;
    String pattern;
    String weight;
    String output_watts;
    String amp_draw;
    String leds;
    String lumens;
    String brand;
    @SerializedName("part_number")
    String manufacturerPartNumber;
    String price;
    @SerializedName("type")
    String vehicleType;
    String image_url;

    String specs = "Light Bar Specs: " +
        "\n Pattern: " + pattern +
        "\n Weight: " + weight +
        "\n Output Watts: " + output_watts +
        "\n Amp Draw: " + amp_draw +
        "\n LEDs: " + leds +
        "\n Lumens: " + lumens;

    public LightBars(AccessoryType accessoryType, String internalPartNumber, String partName, String partDescription, String partBrand, String manufacturerPartNumber, String partPrice, String vehicleTypeThatPartFits, String partSpecs, String partImageURL, String partNumber, String name, String description, String brand, String manufacturerPartNumber1, String price, String vehicleType, String image_url, String specs) {
        super(accessoryType, internalPartNumber, partName, partDescription, partBrand, manufacturerPartNumber, partPrice, vehicleTypeThatPartFits, partSpecs, partImageURL);
        this.partNumber = partNumber;
        this.name = name;
        this.description = description;
        this.brand = brand;
        this.manufacturerPartNumber = manufacturerPartNumber1;
        this.price = price;
        this.vehicleType = vehicleType;
        this.image_url = image_url;
        this.specs = specs;
    }
}
