package com.example.imagetester;


import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import com.google.gson.Gson;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    ImageView img;

    //private RecyclerView myRecyclerView;
    //private RecyclerView.Adapter myAdapter;
    //private RecyclerView.LayoutManager myLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*ArrayList<VehiclePart> cartList = new ArrayList<>();

        cartList.add(new VehiclePart("Price", "Part number", "Part name", "Part description"));
        cartList.add(new VehiclePart("Price 2", "Part number 2", "Part name 2", "Part description 2"));
        cartList.add(new VehiclePart("Price 3", "Part number 3", "Part name 3", "Part description 3"));

        myRecyclerView = findViewById(R.id.cartRecyclerView);
        myRecyclerView.setHasFixedSize(true);
        myLayoutManager = new LinearLayoutManager(this);
        myAdapter = new RecycleViewAdapter(cartList);

        myRecyclerView.setLayoutManager(myLayoutManager);
        myRecyclerView.setAdapter(myAdapter);
        */

        /*
        SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        String cartJson = sharedPref.getString("CART", "");
        String currentVehicleJson = sharedPref.getString("CURRENT_TAB", "");
        String currentTabString = sharedPref.getString("CURRENT_VEHICLE", "");

        Gson gson = new Gson();
        myCartObject cart = gson.fromJson(cartJson, myCartObject.class);
        myVehicleObject currentVehicle = gson.fromJson(currentVehicleJson, myVehicleObject.class);
        */

    }
    /*
    protected void onPause() {


        Gson gson = new Gson();
        String cartJson = gson.toJson(myCartObject);
        String currentVehicleJson = gson.toJson(myVehicleObject);

        SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("CART", cartJson);
        editor.putString("CURRENT_VEHICLE", currentVehicleJson);
        editor.putString("CURRENT_TAB", currentTabString);

        editor.apply();

    }*/

}