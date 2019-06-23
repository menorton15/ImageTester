package com.example.imagetester;

import android.graphics.Bitmap;
import android.graphics.Point;

public abstract class VehiclePart {
    private AccessoryType accessoryType;
    private Float cost;
    private Bitmap partImage;
    private String partID;
    private String partName;
    private String partDescription;
    private String partManufacturer;

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

    abstract Point getReferencePoint(Point anchor);
}
