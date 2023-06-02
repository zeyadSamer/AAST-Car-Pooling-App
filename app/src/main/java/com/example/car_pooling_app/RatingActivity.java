package com.example.car_pooling_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;

import com.example.car_pooling_app.models.OnUpdate;
import com.example.car_pooling_app.models.Trip;
import com.google.gson.Gson;

public class RatingActivity extends AppCompatActivity {


    RatingBar ratingBar;
    Trip trip;

    int rating=5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating);
        ratingBar=findViewById(R.id.driverRatingBar);

        SharedPreferences sPreferences = getSharedPreferences("sPrefEndTrip", Context.MODE_PRIVATE);
        String nameSharedPref = sPreferences.getString("trip", null);
        Gson gson = new Gson();
        trip = gson.fromJson(nameSharedPref, Trip.class);



        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {

                trip.getTripStatus().setDriverRating(ratingBar.getRating());
                trip.getRider().updateData(trip, new OnUpdate() {
                    @Override
                    public void finishTask() {
                        Intent i=new Intent(RatingActivity.this,RiderRequestingScreen.class);
                        finish();
                        startActivity(i);


                    }
                });



            }
        });





    }
}