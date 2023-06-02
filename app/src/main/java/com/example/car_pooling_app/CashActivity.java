package com.example.car_pooling_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.car_pooling_app.models.Trip;
import com.google.gson.Gson;

public class CashActivity extends AppCompatActivity {

    Trip trip;
    TextView amountTextView;
    RatingBar ratingBar;
    float rating;
    Button collectCashButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cash);

        ratingBar=findViewById(R.id.riderRatingbar);

        amountTextView = findViewById(R.id.amountTextView);
        SharedPreferences sPreferences = getSharedPreferences("sPrefEndTrip", Context.MODE_PRIVATE);
        String nameSharedPref = sPreferences.getString("trip", null);
        Gson gson = new Gson();
        trip = gson.fromJson(nameSharedPref, Trip.class);

        amountTextView.setText("EGP " + trip.getAcceptedRequest().getRiderPaymentOffer().toString());




        collectCashButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            Intent i=new Intent(CashActivity.this,IncomingRequestsActivity.class);
            startActivity(i);
            }
        });



    }
}