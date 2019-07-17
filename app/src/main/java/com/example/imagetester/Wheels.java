package com.example.imagetester;

import com.google.gson.annotations.SerializedName;

public class Wheels {
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

    public Wheels(String partNumber, String name, String description, String bolt_circle,
                  String bolt_diameter, String back_spacing, String front_spacing,
                  String negative_offset, String positive_offset, String diameter,
                  String brand, String manufacturerPartNumber, String price,
                  String vehicleType, String image_url) {
        this.partNumber = partNumber;
        this.name = name;
        this.description = description;
        this.bolt_circle = bolt_circle;
        this.bolt_diameter = bolt_diameter;
        this.back_spacing = back_spacing;
        this.front_spacing = front_spacing;
        this.negative_offset = negative_offset;
        this.positive_offset = positive_offset;
        this.diameter = diameter;
        this.brand = brand;
        this.manufacturerPartNumber = manufacturerPartNumber;
        this.price = price;
        this.vehicleType = vehicleType;
        this.image_url = image_url;
    }
}
