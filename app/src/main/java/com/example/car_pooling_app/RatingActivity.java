package com.example.car_pooling_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;

import com.example.car_pooling_app.models.Trip;
import com.google.gson.Gson;

public class RatingActivity extends AppCompatActivity {


    RatingBar ratingBar;
    Trip trip;
    Button collectCashButton;
    int rating=5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating);
        ratingBar=findViewById(R.id.driverRatingBar);
        collectCashButton=findViewById(R.id.paymentButton);
        SharedPreferences sPreferences = getSharedPreferences("sPrefEndTrip", Context.MODE_PRIVATE);
        String nameSharedPref = sPreferences.getString("trip", null);
        Gson gson = new Gson();
        trip = gson.fromJson(nameSharedPref, Trip.class);



        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                rating=(int)v;



            }
        });

        collectCashButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                trip.getTripStatus().setDriverRating((int)rating);
                trip.getRider().updateData(trip);
                trip.getDriver().updateData(trip);

            }
        });




    }
}