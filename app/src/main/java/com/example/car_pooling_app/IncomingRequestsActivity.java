package com.example.car_pooling_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.car_pooling_app.models.Driver;
import com.google.gson.Gson;
//import com.google.gson.Gson;

public class IncomingRequestsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_incoming_requests);

        SharedPreferences sPreferences = getSharedPreferences("sPref",Context.MODE_PRIVATE);
        String nameSharedPref = sPreferences.getString("MyObject",null);
        Gson gson = new Gson();
        Driver driver = gson.fromJson(nameSharedPref,Driver.class);


        /*if(nameSharedPref != null)
        {

        }

        else {

        }*/

    }
}