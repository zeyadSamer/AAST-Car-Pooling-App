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

import com.example.car_pooling_app.models.Trip;
import com.google.gson.Gson;

public class RiderTripActivity extends AppCompatActivity {

    Button cancelTripButton;
    TextView destinationTextView;
    TextView sourceTextView;
    TextView phoneTextView;
    ImageButton callIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rider_trip);

        cancelTripButton = findViewById(R.id.cancelTripButton);
        destinationTextView = findViewById(R.id.riderDestinationAddressView);
        sourceTextView = findViewById(R.id.riderSrcAddressView);
        phoneTextView = findViewById(R.id.driverPhoneNumberView);
        callIcon = findViewById(R.id.driverCallImageButton);


        SharedPreferences sPreferences = getSharedPreferences("sPrefTrip", Context.MODE_PRIVATE);
        String nameSharedPref = sPreferences.getString("trip", null);
        Gson gson = new Gson();
        Trip trip = gson.fromJson(nameSharedPref, Trip.class);

        Log.d("TRIP",trip.getRider().getUsername());

        destinationTextView.setText(trip.getAcceptedRequest().getDestinationAddress());
        sourceTextView.setText(trip.getAcceptedRequest().getSrcAddress());
        phoneTextView.setText(trip.getDriver().getPhoneNumber());

        callIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i =new Intent(Intent.ACTION_DIAL);
                i.setData(Uri.parse("tel:"+trip.getDriver().getPhoneNumber()));
                startActivity(i);
            }
        });



    }
}