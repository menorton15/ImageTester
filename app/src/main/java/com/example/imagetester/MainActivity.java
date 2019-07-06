package com.example.imagetester;

import android.app.Activity;
import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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

import java.io.InputStream;
import java.io.Serializable;
import java.net.URL;
import java.util.ArrayList;

import static java.lang.Float.valueOf;


public class MainActivity extends AppCompatActivity implements AccessoryListRecyclerViewAdapter.OnItemClickListener, View.OnClickListener {

    ImageView img;

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

        Bitmap smallImage = BitmapFactory.decodeResource(getResources(), R.drawable.jeep_tire);
        Log.i("Database", "W: " + smallImage.getWidth() + " H: " + smallImage.getHeight());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        createCartList();
        //buildAccessoryListRecyclerView();
        buildRecyclerView();
        calculateTotalPrice();
        setButtons();
        Display display = getWindowManager().getDefaultDisplay();
        DisplayMetrics outMetrics = new DisplayMetrics ();
        display.getMetrics(outMetrics);

        float density  = getResources().getDisplayMetrics().density;
        float dpHeight = outMetrics.heightPixels / density;
        float dpWidth  = outMetrics.widthPixels / density;

        img = findViewById(R.id.imageView5);

        img.getLayoutParams().height = (int)(dpWidth / 4);
        img.getLayoutParams().width = (int)(dpWidth / 2);

        if (img.getLayoutParams().height > 150 || img.getLayoutParams().width > 300){
            img.getLayoutParams().height = 150;
            img.getLayoutParams().width = 300;
        }
        img.requestLayout();
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
        ///myCartList.add(new VehicleAccessory(AccessoryType.TIRES, " ", " ", " ", " ", " ", "0.00", " ", " ", " "));

    }

    private void buildAccessoryListRecyclerViewForTires() {

        myAccessoryListRecyclerView = findViewById(R.id.accessoryListRecyclerView);
        myAccessoryListRecyclerView.setHasFixedSize(true);
        myAccessoryListRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        myAccessoryList = new ArrayList<>();

        myRequestQueue = Volley.newRequestQueue(this);
        parseJSONTire();
        //parseJSONWheel();
        //parseJSONLightBar();
        //parseJSONShocks();

    }
    private void buildAccessoryListRecyclerViewForWheels() {

        myAccessoryListRecyclerView = findViewById(R.id.accessoryListRecyclerView);
        myAccessoryListRecyclerView.setHasFixedSize(true);
        myAccessoryListRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        myAccessoryList = new ArrayList<>();

        myRequestQueue = Volley.newRequestQueue(this);
        //parseJSONTire();
        parseJSONWheel();
        //parseJSONLightBar();
        //parseJSONShocks();

    }
    private void buildAccessoryListRecyclerViewForLightBars() {

        myAccessoryListRecyclerView = findViewById(R.id.accessoryListRecyclerView);
        myAccessoryListRecyclerView.setHasFixedSize(true);
        myAccessoryListRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        myAccessoryList = new ArrayList<>();

        myRequestQueue = Volley.newRequestQueue(this);
        //parseJSONTire();
        //parseJSONWheel();
        parseJSONLightBar();
        //parseJSONShocks();

    }
    private void buildAccessoryListRecyclerViewForShocks() {

        myAccessoryListRecyclerView = findViewById(R.id.accessoryListRecyclerView);
        myAccessoryListRecyclerView.setHasFixedSize(true);
        myAccessoryListRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        myAccessoryList = new ArrayList<>();

        myRequestQueue = Volley.newRequestQueue(this);
        //parseJSONTire();
        //parseJSONWheel();
        //parseJSONLightBar();
        //parseJSONShocks();

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
        Button buttonTires = findViewById(R.id.button4);
        Button buttonWheels = findViewById(R.id.button6);
        Button buttonLightBars = findViewById(R.id.button7);

        buttonTires.setOnClickListener(this);
        buttonWheels.setOnClickListener(this);
        buttonLightBars.setOnClickListener(this);
        buttonNextActivity.setOnClickListener(this);


        /*buttonNextActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openSendEmailActivity();
            }
        });
        */
    }

    public void removeItem(int position) {
        myCartList.remove(position);
        myCartListAdapter.notifyItemRemoved(position);
    }


    public void openSendEmailActivity(View v) {
        Intent intent = new Intent(this, SendEmailActivity.class);
        intent.putExtra("LIST", (Serializable) myCartList);
        startActivity(intent);
    }

    private void parseJSONShocks() {
        String url = "https://openrpg.org/api/shocks/read.php";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("shocks");

                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject shocks = jsonArray.getJSONObject(i);

                                String shocksPartNumber = shocks.getString("id");
                                String shocksName = shocks.getString("name");
                                String shocksDescription = shocks.getString("description");
                                String shocksBrand = shocks.getString("brand");
                                String shocksManufacturerPartNumber =
                                        shocks.getString("part_number");
                                String shocksPrice = shocks.getString("price");
                                String shocksVehicleType = shocks.getString("type");
                                //String shocksImageUrl = shocks.getString("image_URL");
                                String shocksSpecs = "Shocks Specs: ";
                                String shocksImageUrl = " ";

                                myAccessoryList.add(new VehicleAccessory(AccessoryType.SHOCKS,
                                        shocksPartNumber, shocksName, shocksDescription, shocksBrand,
                                        shocksManufacturerPartNumber, shocksPrice, shocksVehicleType,
                                        shocksSpecs, shocksImageUrl));
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

    private void parseJSONWheel() {
        String url = "https://openrpg.org/api/wheels/read.php";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("light_bars");

                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject wheel = jsonArray.getJSONObject(i);

                                String wheelPartNumber = wheel.getString("id");
                                String wheelName = wheel.getString("name");
                                String wheelDescription = wheel.getString("description");
                                String wheelBoltCircle = wheel.getString("bolt_circle");
                                String wheelBoltDiameter = wheel.getString("bolt_diameter");
                                String wheelBackSpacing = wheel.getString("back_spacing");
                                String wheelFrontSpacing = wheel.getString("front_spacing");
                                String wheelNegativeOffset = wheel.getString("negative_offset");
                                String wheelPositiveOffset = wheel.getString("positive_offset");
                                String wheelDiameter = wheel.getString("diameter");
                                String wheelBrand = wheel.getString("brand");
                                String wheelManufacturerPartNumber =
                                        wheel.getString("part_number");
                                String wheelPrice = wheel.getString("price");
                                //String wheelVehicleType = wheel.getString("type");
                                //String wheelImageUrl = wheel.getString("image_URL");
                                String wheelSpecs = "Wheel specs: " +
                                        "\n Bolt Circle: " + wheelBoltCircle +
                                        "\n Bolt Diameter: " + wheelBoltDiameter +
                                        "\n Back Spacing: " + wheelBackSpacing +
                                        "\n Front Spacing: " + wheelFrontSpacing +
                                        "\n Negative Offset: " + wheelNegativeOffset +
                                        "\n Positive Offset: " + wheelPositiveOffset +
                                        "\n Wheel Diameter: " + wheelDiameter;
                                String wheelVehicleType = "SUV";
                                String wheelImageUrl = " ";

                                myAccessoryList.add(new VehicleAccessory(AccessoryType.WHEEL,
                                        wheelPartNumber, wheelName, wheelDescription, wheelBrand,
                                        wheelManufacturerPartNumber, wheelPrice, wheelVehicleType,
                                        wheelSpecs, wheelImageUrl));
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


    private void parseJSONLightBar() {
        String url = "https://openrpg.org/api/light-bars/read.php";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("light_bars");

                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject lightBar = jsonArray.getJSONObject(i);

                                String lightBarPartNumber = lightBar.getString("id");
                                String lightBarName = lightBar.getString("name");
                                String lightBarDescription = lightBar.getString("description");
                                String lightBarPattern = lightBar.getString("pattern");
                                String lightBarWeight = lightBar.getString("weight");
                                String lightBarOutputWatts = lightBar.getString("output_watts");
                                String lightBarAmpDraw = lightBar.getString("amp_draw");
                                String lightBarLEDs = lightBar.getString("leds");
                                String lightBarLumens = lightBar.getString("lumens");
                                String lightBarBrand = lightBar.getString("brand");
                                String lightBarManufacturerPartNumber =
                                        lightBar.getString("part_number");
                                String lightBarPrice = lightBar.getString("price");
                                //String lightBarVehicleType = lightBar.getString("type");
                                //String lightBarImageUrl = lightBar.getString("image_URL");
                                String lightBarSpecs = "Light Bar Specs: " +
                                        "\n Pattern: " + lightBarPattern +
                                        "\n Weight: " + lightBarWeight +
                                        "\n Output Watts: " +lightBarOutputWatts +
                                        "\n Amp Draw: " + lightBarAmpDraw +
                                        "\n LEDs: " + lightBarLEDs +
                                        "\n Lumens: " + lightBarLumens;
                                String lightBarVehicleType = "SUV";
                                String lightBarImageUrl = " ";

                                myAccessoryList.add(new VehicleAccessory(AccessoryType.LIGHTBAR,
                                        lightBarPartNumber, lightBarName, lightBarDescription, lightBarBrand,
                                        lightBarManufacturerPartNumber, lightBarPrice, lightBarVehicleType,
                                        lightBarSpecs, lightBarImageUrl));
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
                                String tireSpecs = "Tire Size: " + tireCrossSection + "/" +
                                        tireAspectRatio + "R" + tireDiameter;
                                String tireImageUrl = tire.getString("image_url");
                                Log.i("Database", tireImageUrl);
                                ImageFetch fetcher = new ImageFetch(tireImageUrl, img);

                                Thread thread = new Thread(fetcher);

                                thread.start();

                                /**try {
                                    InputStream is = (InputStream) new URL(tireImageUrl).getContent();
                                    Bitmap d = BitmapFactory.decodeStream(is);
                                    BitmapDrawable bd = (BitmapDrawable) img.getDrawable();
                                    Bitmap main = bd.getBitmap();
                                    main = ImageMerger.mergeImages(main, d, 485, 700);
                                    main = ImageMerger.mergeImages(main, d, 1650, 700);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                    Log.i("Database", e.toString());
                                }*/

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


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button2:
                Toast.makeText(this, "This will send your list to email", Toast.LENGTH_SHORT).show();
                openSendEmailActivity(v);
                break;
            case R.id.button4:
                Toast.makeText(this, "Tires List", Toast.LENGTH_SHORT).show();
                buildAccessoryListRecyclerViewForTires();
                break;
            case R.id.button6:
                Toast.makeText(this, "Wheels List", Toast.LENGTH_SHORT).show();
                buildAccessoryListRecyclerViewForWheels();
                break;
            case R.id.button7:
                Toast.makeText(this, "Light Bars List", Toast.LENGTH_SHORT).show();
                buildAccessoryListRecyclerViewForLightBars();
                break;
        }

    }

}