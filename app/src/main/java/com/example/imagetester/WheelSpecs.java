package com.example.imagetester;

public class WheelSpecs {

    String bolt_circle;
    String bolt_diameter;
    String back_spacing;
    String front_spacing;
    String negative_offset;
    String positive_offset;
    String diameter;

    public WheelSpecs(String bolt_circle, String bolt_diameter, String back_spacing,
                      String front_spacing, String negative_offset, String positive_offset,
                      String diameter) {
        this.bolt_circle = bolt_circle;
        this.bolt_diameter = bolt_diameter;
        this.back_spacing = back_spacing;
        this.front_spacing = front_spacing;
        this.negative_offset = negative_offset;
        this.positive_offset = positive_offset;
        this.diameter = diameter;
    }
}
