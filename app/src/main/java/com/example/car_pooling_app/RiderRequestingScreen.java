package com.example.car_pooling_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;

import com.example.car_pooling_app.models.Driver;
import com.example.car_pooling_app.models.Rider;
import com.google.gson.Gson;

public class RiderRequestingScreen extends AppCompatActivity {



    Switch destinationSwitch;
    Button findDriverButton;
    EditText homeAddressEditText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rider_requesting_screen);

        findDriverButton = findViewById(R.id.findDriverButton);
        homeAddressEditText = findViewById(R.id.homeAddressEditText);
        destinationSwitch=findViewById(R.id.destinationSwitch);

        SharedPreferences sPreferences = getSharedPreferences("riderData", Context.MODE_PRIVATE);
        String nameSharedPref = sPreferences.getString("MyObject",null);
        Gson gson = new Gson();
        Rider rider = gson.fromJson(nameSharedPref,Rider.class);

        Log.d("riderinfo",rider.getPhoneNumber());

        findDriverButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Log.d("switch", String.valueOf(destinationSwitch.isChecked()));
            }
        });

    }
}