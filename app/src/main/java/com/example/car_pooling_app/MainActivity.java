package com.example.car_pooling_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.sql.Driver;

public class MainActivity extends AppCompatActivity {

    Button RiderButton;
    Button DriverButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RiderButton=findViewById(R.id.rider_button);
        DriverButton=findViewById(R.id.driver_button);
        RiderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i= new Intent(MainActivity.this,RiderRegistration.class);
                startActivity(i);




            }
        });

        DriverButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i= new Intent(MainActivity.this, DriverRegistration.class);
                startActivity(i);
            }
        });








    }








}