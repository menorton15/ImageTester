package com.example.imagetester;

import java.io.Serializable;

public class VehicleAccessory implements Serializable {

    private AccessoryType accessoryType;
    private String internalPartNumber;
    private String partName;
    private String partDescription;
    private String partBrand;
    private String manufacturerPartNumber;
    private String partPrice;
    private String vehicleTypeThatPartFits;
    private String partSpecs;
    private String partImageURL;



    public AccessoryType getAccessoryType() {
        return accessoryType;
    }

    public void setAccessoryType(AccessoryType accessoryType) {
        this.accessoryType = accessoryType;
    }

    public String getInternalPartNumber() {
        return internalPartNumber;
    }

    public VehicleAccessory(AccessoryType accessoryType, String internalPartNumber, String partName,
                            String partDescription, String partBrand, String manufacturerPartNumber,
                            String partPrice, String vehicleTypeThatPartFits, String partSpecs,
                            String partImageURL) {
        this.accessoryType = accessoryType;
        this.internalPartNumber = internalPartNumber;
        this.partName = partName;
        this.partDescription = partDescription;
        this.partBrand = partBrand;
        this.manufacturerPartNumber = manufacturerPartNumber;
        this.partPrice = partPrice;
        this.vehicleTypeThatPartFits = vehicleTypeThatPartFits;
        this.partSpecs = partSpecs;
        this.partImageURL = partImageURL;
    }

    public void setInternalPartNumber(String internalPartNumber) {
        this.internalPartNumber = internalPartNumber;
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

    public String getPartBrand() {
        return partBrand;
    }

    public void setPartBrand(String partBrand) {
        this.partBrand = partBrand;
    }

    public String getManufacturerPartNumber() {
        return manufacturerPartNumber;
    }

    public void setManufacturerPartNumber(String manufacturerPartNumber) {
        this.manufacturerPartNumber = manufacturerPartNumber;
    }

    public String getPartPrice() {
        return partPrice;
    }

    public void setPartPrice(String partPrice) {
        this.partPrice = partPrice;
    }

    public String getVehicleTypeThatPartFits() {
        return vehicleTypeThatPartFits;
    }

    public void setVehicleTypeThatPartFits(String vehicleTypeThatPartFits) {
        this.vehicleTypeThatPartFits = vehicleTypeThatPartFits;
    }

    public String getPartImageURL() {
        return partImageURL;
    }

    public void setPartImageURL(String partImageURL) {
        this.partImageURL = partImageURL;
    }

    public String getPartSpecs() {
        return partSpecs;
    }

    public void setPartSpecs(String partSpecs) {
        this.partSpecs = partSpecs;
    }
}

