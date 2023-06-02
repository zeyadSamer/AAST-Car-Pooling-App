package com.example.car_pooling_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import com.example.car_pooling_app.models.Trip;
import com.google.gson.Gson;

public class CashActivity extends AppCompatActivity {

    Trip trip;
    TextView amountTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cash);

        amountTextView = findViewById(R.id.amountTextView);
        SharedPreferences sPreferences = getSharedPreferences("sPrefEndTrip", Context.MODE_PRIVATE);
        String nameSharedPref = sPreferences.getString("trip", null);
        Gson gson = new Gson();
        trip = gson.fromJson(nameSharedPref, Trip.class);

        amountTextView.setText("EGP " + trip.getAcceptedRequest().getRiderPaymentOffer().toString());

    }
}