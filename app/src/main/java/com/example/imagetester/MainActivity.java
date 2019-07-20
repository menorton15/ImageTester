package com.example.imagetester;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;

import static java.lang.Float.valueOf;


public class MainActivity extends AppCompatActivity implements
        AccessoryListRecyclerViewAdapter.OnItemClickListener, View.OnClickListener,
        AdapterView.OnItemSelectedListener {

    private ImageView img;

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
    private String currentVehicleType;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setLogo();
        setSpinners();
        setButtons();
        loadSavedData();
        buildRecyclerView();
        calculateTotalPrice();
        displayImage();
    }

    protected void onPause() {
        super.onPause();
        saveDataToSharedPreferences();
    }

    private void loadSavedData() {
        /*get JSON string from Shared Preferences and use gson to convert to an ArrayList
             of VehicleAccessory objects and add list to myCartList*/
        SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        String cartJson = sharedPref.getString("CART", "");
        Type type = new TypeToken<ArrayList<VehicleAccessory>>(){}.getType();
        Gson gson = new Gson();
        myCartList = gson.fromJson(cartJson, type);

        if(myCartList == null) {
            myCartList = new ArrayList<>();
        }
    }

    private void saveDataToSharedPreferences() {
        /*use gson to save the ArrayList of VehicleAccessory objects in myCartLIst to JSON string
             and save to SharedPreferences */
        Gson gson = new Gson();
        String cartJson = gson.toJson(myCartList);

        SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("CART", cartJson);
        editor.apply();
    }

    private void buildAccessoryListRecyclerView() {
        //display list of accessories using RecyclerView widget and CardView widget
        myAccessoryListRecyclerView = findViewById(R.id.accessoryListRecyclerView);
        myAccessoryListRecyclerView.setHasFixedSize(true);
        myAccessoryListRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        myAccessoryListRecyclerViewAdapter = new AccessoryListRecyclerViewAdapter(this,
                myAccessoryList);
        myAccessoryListRecyclerView.setAdapter(myAccessoryListRecyclerViewAdapter);
        myAccessoryListRecyclerViewAdapter.setOnItemClickListener(this);
    }

    private void buildRecyclerView() {
        //display user selected items in a list using RecyclerView widget and CardView widget
        myRecyclerView = findViewById(R.id.cartRecyclerView);
        myRecyclerView.setHasFixedSize(true);
        myLayoutManager = new LinearLayoutManager(this);
        myCartListAdapter = new CartListRecyclerViewAdapter(myCartList);

        myRecyclerView.setLayoutManager(myLayoutManager);
        myRecyclerView.setAdapter(myCartListAdapter);
        myCartListAdapter.setOnItemClickListener(new CartListRecyclerViewAdapter.OnItemClickListener() {

            @Override
            public void onDeleteClick(int position) {
                //deleted selected item from list when trash icon is clicked
                myCartList.remove(position);
                myCartListAdapter.notifyItemRemoved(position);
                calculateTotalPrice();
            }
        });
    }

    private void calculateTotalPrice() {
        //calculate and display total price of all accessories in cart
        Float total = 0f;

        for (int i = 0; i < myCartList.size(); i++) {
            total += valueOf(myCartList.get(i).getPartPrice());
        }
        totalPriceTextView = findViewById(R.id.totalPrice);
        totalPriceTextView.setText("Total: $ " + String.format("%.2f", total));
    }

    private void displayImage() {
        //display Jeep image or Polaris image depending on which is selected in the spinner
        img = findViewById(R.id.imageView5);

        if(currentVehicleType == "SUV"){
            Picasso.get().load(R.drawable.jeep_body).fit().centerInside().into(img);
        }
        else if(currentVehicleType == "UTV") {
            Picasso.get().load(R.drawable.rzr_4_seat).fit().centerInside().into(img);
        }
    }

    private void setButtons() {
        Button buttonTires = findViewById(R.id.button_tires);
        Button buttonWheels = findViewById(R.id.button_wheels);
        Button buttonLightBars = findViewById(R.id.button_light_bars);
        Button buttonShocks = findViewById(R.id.button_shocks);
        Button buttonLiftKits = findViewById(R.id.button_lift_kits);
        buttonNextActivity = findViewById(R.id.button_send_to_email);

        buttonTires.setOnClickListener(this);
        buttonWheels.setOnClickListener(this);
        buttonLightBars.setOnClickListener(this);
        buttonShocks.setOnClickListener(this);
        buttonLiftKits.setOnClickListener(this);
        buttonNextActivity.setOnClickListener(this);
    }

    private void setSpinners() {
        //Year Spinner
        Spinner spinnerYear = findViewById(R.id.spinnerYear);
        ArrayAdapter<CharSequence> adapterYear = ArrayAdapter.createFromResource(this,
                R.array.year, android.R.layout.simple_spinner_item);
        adapterYear.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerYear.setAdapter(adapterYear);
        spinnerYear.setOnItemSelectedListener(this);

        //Make Spinner
        Spinner spinnerVehicleType = findViewById(R.id.spinnerVehicleType);
        ArrayAdapter<CharSequence> adapterVehicleType = ArrayAdapter.createFromResource(this,
                R.array.vehicleType, android.R.layout.simple_spinner_item);
        adapterVehicleType.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerVehicleType.setAdapter(adapterVehicleType);
        spinnerVehicleType.setOnItemSelectedListener(this);

    }

    private void setLogo() {
        //Action Bar Logo insert
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.drawable.boise4x4logo);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        setSpinners();
    }

    private void openSendEmailActivity(View v) {
        //open new activity
        Intent intent = new Intent(this, SendEmailActivity.class);
        intent.putExtra("LIST", (Serializable) myCartList);
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        //define actions for all buttons in activity
        switch (v.getId()) {
            case R.id.button_send_to_email:
                //go to email activity
                Toast.makeText(this, "This will send your list to email",
                        Toast.LENGTH_SHORT).show();
                openSendEmailActivity(v);
                break;
            case R.id.button_tires:
                //populate list of tires from JSON data stored in a local asset file
                Toast.makeText(this, "Tires List", Toast.LENGTH_SHORT).show();
                GetJSONTiresFromAssets getTires = new GetJSONTiresFromAssets(this);
                myAccessoryList = getTires.getJSONTires(currentVehicleType);
                buildAccessoryListRecyclerView();
                break;
            case R.id.button_wheels:
                //populate list of wheels from JSON data stored in a local asset file
                Toast.makeText(this, "Wheels List", Toast.LENGTH_SHORT).show();
                GetJSONWheelsFromAssets getWheels = new GetJSONWheelsFromAssets(this);
                myAccessoryList = getWheels.getJSONWheels(currentVehicleType);
                buildAccessoryListRecyclerView();
                break;
            case R.id.button_light_bars:
                //populate list of light bars from JSON data stored in a local asset file
                Toast.makeText(this, "Light Bars List", Toast.LENGTH_SHORT).show();
                GetJSONLightBarFromAssets getLightBars = new GetJSONLightBarFromAssets(this);
                myAccessoryList = getLightBars.getJSONLightBar(currentVehicleType);
                buildAccessoryListRecyclerView();
                break;
            case R.id.button_shocks:
                //populate list of shocks from JSON data stored in a local asset file
                Toast.makeText(this, "Shocks List", Toast.LENGTH_SHORT).show();
                GetJSONShocksFromAssets getShocks = new GetJSONShocksFromAssets(this);
                myAccessoryList = getShocks.getJSONShocks(currentVehicleType);
                buildAccessoryListRecyclerView();
                break;
            case R.id.button_lift_kits:
                //populate list of lift kits from JSON data stored in a local asset file
                Toast.makeText(this, "Lift Kits List", Toast.LENGTH_SHORT).show();
                GetJSONLiftKitsFromAssets getLiftKits = new GetJSONLiftKitsFromAssets(this);
                myAccessoryList = getLiftKits.getJSONLiftKits(currentVehicleType);
                buildAccessoryListRecyclerView();
                break;
        }
    }

    @Override
    public void onAddToCartIconClick(int position) {
        /*when cart icon in an accessory list item is clicked, the item will be added to the cart
            and the total price will be updated */
        VehicleAccessory clickedItem = myAccessoryList.get(position);
        int cartPosition = myCartList.size();
        myCartList.add(cartPosition, clickedItem);
        calculateTotalPrice();
        myCartListAdapter.notifyItemInserted(cartPosition);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        /*when vehicle make is changed in the spinner, the current accessory list will be cleared
             and the current vehicle make will be saved so that the accessory list will populate
             with only the accessories appropriate for that vehicle type */
        String text = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(), text, Toast.LENGTH_SHORT).show();
        if(text.equals("Jeep"))
            currentVehicleType = "SUV";
        else if(text.equals("Polaris"))
            currentVehicleType = "UTV";
        myAccessoryList = null;
        buildAccessoryListRecyclerView();
        displayImage();

        Log.i("SpinnerVariable", "" + currentVehicleType);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}