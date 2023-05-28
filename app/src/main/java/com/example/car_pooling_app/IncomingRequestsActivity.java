package com.example.car_pooling_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.car_pooling_app.models.Driver;
import com.google.gson.Gson;

public class IncomingRequestsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_incoming_requests);
        TextView testing = findViewById(R.id.textView);

        SharedPreferences mPrefs = getPreferences(MODE_PRIVATE);
        String nameSharedPref = mPrefs.getString("name",null);
        if(nameSharedPref != null)
        {
            testing.setText("TESTING COMPLETE");
        }

        //Gson gson = new Gson();
        //String json = mPrefs.getString("MyObject", "");
        //Driver driver = gson.fromJson(json, Driver.class);
       // Log.d("yarab"," "+driver.getPhoneNumber());





    }
}