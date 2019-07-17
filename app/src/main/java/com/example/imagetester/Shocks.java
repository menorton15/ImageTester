package com.example.imagetester;

import com.google.gson.annotations.SerializedName;

public class Shocks {

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

    public Shocks(String partNumber, String name, String description, String brand,
                  String manufacturerPartNumber, String price, String vehicleType,
                  String image_url) {
        this.partNumber = partNumber;
        this.name = name;
        this.description = description;
        this.brand = brand;
        this.manufacturerPartNumber = manufacturerPartNumber;
        this.price = price;
        this.vehicleType = vehicleType;
        this.image_url = image_url;
    }
}
