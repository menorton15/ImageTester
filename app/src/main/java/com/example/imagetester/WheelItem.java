package com.example.imagetester;

import com.google.gson.annotations.SerializedName;

public class WheelItem extends VehicleAccessory{
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

    String back_spacing;
    String size;
    String offset;
    String bolt_pattern;
    String weight;

    String specs;

    public WheelItem(AccessoryType accessoryType, String internalPartNumber, String partName,
                     String partDescription, String partBrand, String manufacturerPartNumber,
                     String partPrice, String vehicleTypeThatPartFits, String partSpecs,
                     String partImageURL) {
        super(accessoryType, internalPartNumber, partName, partDescription, partBrand,
                manufacturerPartNumber, partPrice, vehicleTypeThatPartFits, partSpecs,
                partImageURL);

    }

    public String getSpecs() {

        specs = "Wheel Specs: \n" +
                "Size: " + size + "\n" +
                "Offset: " + offset + "\n" +
                "Weight: " + weight + "\n" +
                "Bolt Pattern: " + bolt_pattern + "\n" +
                "Back Spacing: " + back_spacing;

        return specs;
    }

    public void setSpecs(String specs) {
        this.specs = specs;
    }
}
