package com.example.imagetester;

import android.graphics.Bitmap;
import android.graphics.Point;

public abstract class Accessory {
    private Bitmap image;

    public Accessory(){

    }

    abstract Point getReferencePoint(Point anchor);

    float getCost(){
        return 0f;
    }

    String getProductInfo(){
        return null;
    }

    Bitmap getImage(){
        return image;
    }

}
