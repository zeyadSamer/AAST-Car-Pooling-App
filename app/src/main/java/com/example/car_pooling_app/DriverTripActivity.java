package com.example.car_pooling_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;


import com.example.car_pooling_app.models.Driver;
import com.example.car_pooling_app.models.Trip;
import com.google.gson.Gson;

public class DriverTripActivity extends AppCompatActivity {

    Button arrivedButton;
    TextView destinationTextView;
    TextView sourceTextView;
    TextView phoneTextView;
    ImageButton callIcon;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_trip);
        arrivedButton = findViewById(R.id.arrivedButton);
        destinationTextView = findViewById(R.id.destinationAddressView);
        sourceTextView = findViewById(R.id.srcAddressView);
        phoneTextView = findViewById(R.id.riderPhoneNumberView);
        callIcon = findViewById(R.id.riderCallImageButton);

        SharedPreferences sPreferences = getSharedPreferences("sPref", Context.MODE_PRIVATE);
        String nameSharedPref = sPreferences.getString("trip", null);
        Gson gson = new Gson();
        Trip trip = gson.fromJson(nameSharedPref, Trip.class);

        Log.d("DRIVER",trip.getDriver().getUsername());

        destinationTextView.setText(trip.getAcceptedRequest().getDestinationAddress());
        sourceTextView.setText(trip.getAcceptedRequest().getSrcAddress());
        phoneTextView.setText(trip.getRider().getPhoneNumber());

        callIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i =new Intent(Intent.ACTION_DIAL);
                i.setData(Uri.parse("tel:"+trip.getRider().getPhoneNumber()));
                startActivity(i);
            }
        });

        arrivedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                if(arrivedButton.getText().equals("Arrived?")) {


                    trip.getTripStatus().setReachedRider(true);
                    trip.getDriver().updateData(trip);
                    trip.getRider().updateData(trip);

                    arrivedButton.setText("Start Trip");

                }else if(arrivedButton.getText().equals("Start Trip")){
                    arrivedButton.setText("End Trip");
                    trip.getTripStatus().setTripStarted(true);

                    trip.getDriver().updateData(trip);
                    trip.getRider().updateData(trip);

                    //Go to cash actiivity




                }




            }
        });


    }
}