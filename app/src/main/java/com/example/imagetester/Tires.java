package com.example.imagetester;

import com.google.gson.annotations.SerializedName;

public class Tires {

    @SerializedName("id")
    String partNumber;
    String name;
    String description;
    String cross_section;
    String aspect_ratio;
    String diameter;
    String brand;
    @SerializedName("part_number")
    String manufacturerPartNumber;
    String price;
    @SerializedName("type")
    String vehicleType;
    String image_url;

    public Tires(String partNumber, String name, String description, String cross_section,
                 String aspect_ratio, String diameter, String brand, String manufacturerPartNumber,
                 String price, String vehicleType, String image_url) {
        this.partNumber = partNumber;
        this.name = name;
        this.description = description;
        this.cross_section = cross_section;
        this.aspect_ratio = aspect_ratio;
        this.diameter = diameter;
        this.brand = brand;
        this.manufacturerPartNumber = manufacturerPartNumber;
        this.price = price;
        this.vehicleType = vehicleType;
        this.image_url = image_url;
    }
}
