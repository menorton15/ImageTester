package com.example.imagetester;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class SendEmailActivity extends AppCompatActivity {

    private ArrayList<VehicleAccessory> myCartList;

    private RecyclerView myRecyclerView;
    private CartListRecyclerViewAdapter myAdapter;
    private RecyclerView.LayoutManager myLayoutManager;

    private EditText myEditTextTo;
    private EditText myEditTextSubject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_email);

        myEditTextTo = findViewById(R.id.edit_text_to);
        myEditTextSubject = findViewById(R.id.edit_text_subject);
        Intent i = getIntent();
        myCartList = (ArrayList<VehicleAccessory>) i.getSerializableExtra("LIST");

        buildRecyclerView();

        Button buttonSend = findViewById(R.id.button_send);
        buttonSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMail();
            }
        });
    }

    private void sendMail() {
        String recipientList = "wmaxwell@jm-astro.com," + myEditTextTo.getText().toString();
        String[] recipients = recipientList.split(",");

        String subject = myEditTextSubject.getText().toString();

        String message = "Wish List: \n";

        for(int i = 0; i < myCartList.size(); i++) {
            message += myCartList.get(i).getPartName() + ", $ " + myCartList.get(i).getPartPrice() + "\n";
        }

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_EMAIL, recipients);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, message);

        intent.setType("message/rfc822");
        startActivity(Intent.createChooser(intent, "Choose an email client"));
    }

    public void buildRecyclerView() {

        myRecyclerView = findViewById(R.id.cartRecyclerView);
        myRecyclerView.setHasFixedSize(true);
        myLayoutManager = new LinearLayoutManager(this);
        myAdapter = new CartListRecyclerViewAdapter(myCartList);

        myRecyclerView.setLayoutManager(myLayoutManager);
        myRecyclerView.setAdapter(myAdapter);
    }
}
