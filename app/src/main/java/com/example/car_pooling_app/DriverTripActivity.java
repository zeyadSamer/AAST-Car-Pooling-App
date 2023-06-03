package com.example.car_pooling_app;

import androidx.annotation.Nullable;
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
import com.example.car_pooling_app.models.OnUpdate;
import com.example.car_pooling_app.models.Rider;
import com.example.car_pooling_app.models.Trip;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.gson.Gson;

import java.util.Date;
import java.util.Objects;

public class DriverTripActivity extends AppCompatActivity {

    Button arrivedButton;
    TextView destinationTextView;
    TextView sourceTextView;
    TextView phoneTextView;
    ImageButton callIcon;
    Trip trip ;

    Boolean ourTripFound=false;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_trip);
        arrivedButton = findViewById(R.id.arrivedButton);
        destinationTextView = findViewById(R.id.destinationAddressView);
        sourceTextView = findViewById(R.id.srcAddressView);
        phoneTextView = findViewById(R.id.riderPhoneNumberView);
        callIcon = findViewById(R.id.riderCallImageButton);


        OnUpdate onUpdate=new OnUpdate() {
            @Override
            public void finishTask() {

            }
        };

        SharedPreferences sPreferences = getSharedPreferences("sPref", Context.MODE_PRIVATE);
        String nameSharedPref = sPreferences.getString("trip", null);
        Gson gson = new Gson();
         trip = gson.fromJson(nameSharedPref, Trip.class);

        Log.d("DRIVER",trip.getDriver().getUsername());

        destinationTextView.setText(trip.getAcceptedRequest().getDestinationAddress());
        sourceTextView.setText(trip.getAcceptedRequest().getSrcAddress());
        phoneTextView.setText(trip.getRider().getPhoneNumber());

        Date date=new Date();





        Driver.firebaseFirestore.collection("drivers").document("driver:" + trip.getRider().getEmail()).collection("trips").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {


                //when collection changes after starting ride this means trip is canceled and deleted
                for(DocumentChange dc: value.getDocumentChanges()) {

                    if (Objects.equals(dc.getDocument().toObject(Trip.class).getDriver().getEmail(), trip.getDriver().getEmail()) &&
                            Objects.equals(dc.getDocument().toObject(Trip.class).getRider().getEmail(), trip.getRider().getEmail()) &&
                            dc.getDocument().toObject(Trip.class).getTripStatus().isCompleted() == trip.getTripStatus().isCompleted()) {
                        ourTripFound = true;

                    }
                }

                if(!ourTripFound) {

                    SharedPreferences riderData = getSharedPreferences("sPrefEndTrip", MODE_PRIVATE);
                    SharedPreferences.Editor editor = riderData.edit();
                    Gson gson = new Gson();
                    String json = gson.toJson(trip);
                    editor.putString("trip", json);
                    editor.apply();
                    Intent intent = new Intent(DriverTripActivity.this, IncomingRequestsActivity.class);

                    startActivity(intent);
                    finish();
                }

                }



        });










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
                    trip.getDriver().updateData(trip,onUpdate);
                    trip.getRider().updateData(trip,onUpdate);

                    arrivedButton.setText("Start Trip");

                }else if(arrivedButton.getText().equals("Start Trip")){

                    arrivedButton.setText("End Trip");
                    trip.getTripStatus().setTripStarted(true);

                    trip.getDriver().updateData(trip, onUpdate);
                    trip.getRider().updateData(trip, onUpdate);





                }else if (arrivedButton.getText().equals("End Trip")){

                    Log.d("finalerror","entered");


                    trip.getTripStatus().setCompleted(true);
                    trip.getDriver().updateData(trip,onUpdate);
                    trip.getRider().updateData(trip, onUpdate);



                    SharedPreferences sPreferences = getSharedPreferences("sPrefEndTrip",Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sPreferences.edit();
                    Gson gson = new Gson();
                    String json = gson.toJson(trip);
                    editor.putString("trip", json);
                    editor.apply();
                    Intent intent=new Intent(DriverTripActivity.this, CashActivity.class);
                    startActivity(intent);

                }




            }
        });


    }
}