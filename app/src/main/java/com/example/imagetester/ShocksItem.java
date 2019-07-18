package com.example.imagetester;

import com.google.gson.annotations.SerializedName;

public class ShocksItem extends VehicleAccessory {

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

    String specs;


    public ShocksItem(AccessoryType accessoryType, String internalPartNumber,
                      String partName, String partDescription, String partBrand,
                      String manufacturerPartNumber, String partPrice,
                      String vehicleTypeThatPartFits, String partSpecs, String partImageURL) {
        super(accessoryType, internalPartNumber, partName, partDescription, partBrand,
                manufacturerPartNumber, partPrice, vehicleTypeThatPartFits,
                partSpecs, partImageURL);
    }

    public String getSpecs() {

        specs = " ";
        return specs;
    }

    public void setSpecs(String specs) {
        this.specs = specs;
    }
}
