package com.example.imagetester;


import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;

import static java.lang.Float.valueOf;


public class MainActivity extends AppCompatActivity implements AccessoryListRecyclerViewAdapter.OnItemClickListener {

    //ImageView img;

    private RecyclerView myRecyclerView;
    private CartListRecyclerViewAdapter myCartListAdapter;
    private RecyclerView.LayoutManager myLayoutManager;

    private RecyclerView myAccessoryListRecyclerView;
    private AccessoryListRecyclerViewAdapter myAccessoryListRecyclerViewAdapter;
    private RequestQueue myRequestQueue;

    private ArrayList<VehicleAccessory> myCartList;
    private ArrayList<VehicleAccessory> myAccessoryList;

    private TextView totalPriceTextView;

    private Button buttonNextActivity;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        createCartList();
        buildAccessoryListRecyclerView();
        buildRecyclerView();
        calculateTotalPrice();
        setButtons();
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

    @Override
    public void onAddToCartIconClick(int position) {

        VehicleAccessory clickedItem = myAccessoryList.get(position);

        int cartPosition = myCartList.size();

        myCartList.add(cartPosition, clickedItem);
        calculateTotalPrice();
        myCartListAdapter.notifyItemInserted(cartPosition);
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

    public void createCartList() {

        myCartList = new ArrayList<>();
        myCartList.add(new VehicleAccessory(AccessoryType.TIRES, " ", " ", " ", " ", " ", "0.00", " ", " ", " "));

    }

    private void buildAccessoryListRecyclerView() {

        myAccessoryListRecyclerView = findViewById(R.id.accessoryListRecyclerView);
        myAccessoryListRecyclerView.setHasFixedSize(true);
        myAccessoryListRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        myAccessoryList = new ArrayList<>();

        myRequestQueue = Volley.newRequestQueue(this);
        parseJSONTire();

    }

    private void parseJSONTire() {
        String url = "https://openrpg.org/api/tires/read.php";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("tires");

                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject tire = jsonArray.getJSONObject(i);

                                String tirePartNumber = tire.getString("id");
                                String tireName = tire.getString("name");
                                String tireDescription = tire.getString("description");
                                String tireCrossSection = tire.getString("cross_section");
                                String tireAspectRatio = tire.getString("aspect_ratio");
                                String tireDiameter = tire.getString("diameter");
                                String tireBrand = tire.getString("brand");
                                String tireManufacturerPartNumber =
                                        tire.getString("part_number");
                                String tirePrice = tire.getString("price");
                                String tireVehicleType = tire.getString("type");
                                //String tireImageUrl = tire.getString("image_URL");
                                String tireSpecs = "Tire Size: " + tireCrossSection + "/" +
                                        tireAspectRatio + "R" + tireDiameter;
                                String tireImageUrl = " ";

                                myAccessoryList.add(new VehicleAccessory(AccessoryType.TIRES,
                                        tirePartNumber, tireName, tireDescription, tireBrand,
                                        tireManufacturerPartNumber, tirePrice, tireVehicleType,
                                        tireSpecs, tireImageUrl));
                            }

                            myAccessoryListRecyclerViewAdapter =
                                    new AccessoryListRecyclerViewAdapter(MainActivity.this,
                                            myAccessoryList);
                            myAccessoryListRecyclerView.setAdapter(myAccessoryListRecyclerViewAdapter);
                            myAccessoryListRecyclerViewAdapter.setOnItemClickListener(MainActivity.this);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        myRequestQueue.add(request);
    }


    public void buildRecyclerView() {

        myRecyclerView = findViewById(R.id.cartRecyclerView);
        myRecyclerView.setHasFixedSize(true);
        myLayoutManager = new LinearLayoutManager(this);
        myCartListAdapter = new CartListRecyclerViewAdapter(myCartList);

        myRecyclerView.setLayoutManager(myLayoutManager);
        myRecyclerView.setAdapter(myCartListAdapter);

        myCartListAdapter.setOnItemClickListener(new CartListRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {

            }

            @Override
            public void onDeleteClick(int position) {
                removeItem(position);
                calculateTotalPrice();
            }
        });
    }

    public void calculateTotalPrice() {
        Float total = 0f;

        for (int i = 0; i < myCartList.size(); i++) {
            total += valueOf(myCartList.get(i).getPartPrice());
        }
        totalPriceTextView = findViewById(R.id.totalPrice);
        totalPriceTextView.setText("Total: $ " + String.format("%.2f", total));
    }

    public void setButtons() {

        buttonNextActivity = findViewById(R.id.button2);

        buttonNextActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openSendEmailActivity();
            }
        });
    }

    public void removeItem(int position) {
        myCartList.remove(position);
        myCartListAdapter.notifyItemRemoved(position);
    }


    public void openSendEmailActivity() {
        Intent intent = new Intent(this, SendEmailActivity.class);
        intent.putExtra("LIST", (Serializable) myCartList);
        startActivity(intent);
    }

}