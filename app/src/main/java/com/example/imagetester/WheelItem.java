package com.example.imagetester;

import com.google.gson.annotations.SerializedName;

public class WheelItem extends VehicleAccessory{
    @SerializedName("id")
    String partNumber;
    String name;
    String description;
    String bolt_circle;
    String bolt_diameter;
    String back_spacing;
    String front_spacing;
    String negative_offset;
    String positive_offset;
    String diameter;
    String brand;
    @SerializedName("part_number")
    String manufacturerPartNumber;
    String price;
    @SerializedName("type")
    String vehicleType;
    String image_url;

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
                "Bolt Circle: " + bolt_circle + "\n" +
                "Bolt Diameter: " + bolt_diameter + "\n" +
                "Back Spacing: " + back_spacing + "\n" +
                "Front Spacing: " + front_spacing + "\n" +
                "Negative Offset: " + negative_offset + "\n" +
                "Positive Offset: " + positive_offset + "\n" +
                "Diameter: " + diameter;

        return specs;
    }

    public void setSpecs(String specs) {
        this.specs = specs;
    }
}
