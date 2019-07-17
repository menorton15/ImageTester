package com.example.imagetester;

public class LightBarSpecs {

    String pattern;
    String weight;
    String output_watts;
    String amp_draw;
    String leds;
    String lumens;

    public LightBarSpecs(String pattern, String weight, String output_watts,
                         String amp_draw, String leds, String lumens) {
        this.pattern = pattern;
        this.weight = weight;
        this.output_watts = output_watts;
        this.amp_draw = amp_draw;
        this.leds = leds;
        this.lumens = lumens;
    }
}
