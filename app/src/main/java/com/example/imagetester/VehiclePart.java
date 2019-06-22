package com.example.imagetester;

import android.graphics.Bitmap;

public class VehiclePart {
    private AccessoryType accessoryType;
    private Float cost;
    private Bitmap partImage;
    private String partID;
    private String partName;
    private String partDescription;
    private String partManufacturer;
    private String priceAsString;


    public VehiclePart(AccessoryType accessoryType, Float cost, Bitmap partImage,
                       String partID, String partName, String partDescription, String partManufacturer) {
        this.accessoryType = accessoryType;
        this.cost = cost;
        this.partImage = partImage;
        this.partID = partID;
        this.partName = partName;
        this.partDescription = partDescription;
        this.partManufacturer = partManufacturer;
    }

    public VehiclePart(String priceAsString, String partID, String partName, String partDescription) {
        this.priceAsString = priceAsString;
        this.partID = partID;
        this.partName = partName;
        this.partDescription = partDescription;
    }

    public AccessoryType getAccessoryType() {
        return accessoryType;
    }

    public void setAccessoryType(AccessoryType accessoryType) {
        this.accessoryType = accessoryType;
    }

    public Float getCost() {
        return cost;
    }

    public void setCost(Float cost) {
        this.cost = cost;
    }

    public Bitmap getPartImage() {
        return partImage;
    }

    public void setPartImage(Bitmap partImage) {
        this.partImage = partImage;
    }

    public String getPartID() {
        return partID;
    }

    public void setPartID(String partID) {
        this.partID = partID;
    }

    public String getPartName() {
        return partName;
    }

    public void setPartName(String partName) {
        this.partName = partName;
    }

    public String getPartDescription() {
        return partDescription;
    }

    public void setPartDescription(String partDescription) {
        this.partDescription = partDescription;
    }

    public String getPartManufacturer() {
        return partManufacturer;
    }

    public void setPartManufacturer(String partManufacturer) {
        this.partManufacturer = partManufacturer;
    }

    public String getPriceAsString() {
        return priceAsString;
    }

    public void setPriceAsString(String priceAsString) {
        this.priceAsString = priceAsString;
    }
}
