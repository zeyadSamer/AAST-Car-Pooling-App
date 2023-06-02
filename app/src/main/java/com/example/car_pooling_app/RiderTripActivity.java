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
import android.widget.Toast;

import com.example.car_pooling_app.models.Rider;
import com.example.car_pooling_app.models.Trip;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.gson.Gson;

import java.util.Date;

public class RiderTripActivity extends AppCompatActivity {


    TextView statusMessageTextView;
    Button cancelTripButton;
    TextView destinationTextView;
    TextView sourceTextView;
    TextView phoneTextView;
    ImageButton callIcon;
    Trip trip;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rider_trip);

        cancelTripButton = findViewById(R.id.cancelTripButton);
        destinationTextView = findViewById(R.id.riderDestinationAddressView);
        sourceTextView = findViewById(R.id.riderSrcAddressView);
        phoneTextView = findViewById(R.id.driverPhoneNumberView);
        callIcon = findViewById(R.id.driverCallImageButton);
        statusMessageTextView = findViewById(R.id.riderWaitingtextView);

        SharedPreferences sPreferences = getSharedPreferences("sPrefTrip", Context.MODE_PRIVATE);
        String nameSharedPref = sPreferences.getString("trip", null);
        Gson gson = new Gson();
        trip = gson.fromJson(nameSharedPref, Trip.class);

        Log.d("TRIP", trip.getRider().getUsername());

        destinationTextView.setText(trip.getAcceptedRequest().getDestinationAddress());
        sourceTextView.setText(trip.getAcceptedRequest().getSrcAddress());
        phoneTextView.setText(trip.getDriver().getPhoneNumber());

        callIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_DIAL);
                i.setData(Uri.parse("tel:" + trip.getDriver().getPhoneNumber()));
                startActivity(i);
            }
        });
        Date date = new Date();

        Rider.firebaseFirestore.collection("riders").document("rider:" + trip.getRider().getEmail()).collection("trips").document("trip:" + date.getHours() + "-" + date.getDay() + "-" + date.getMonth() + "-" + date.getYear()).addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                trip = value.toObject(Trip.class);

                if(trip.getTripStatus().isCompleted()){
                    SharedPreferences riderData = getSharedPreferences("sPrefEndTrip",MODE_PRIVATE);
                    SharedPreferences.Editor editor = riderData.edit();
                    Gson gson = new Gson();
                    String json = gson.toJson(trip);
                    editor.putString("trip", json);
                    editor.apply();
                    Intent intent = new Intent(RiderTripActivity.this, RatingActivity.class);


                    startActivity(intent);
                    finish();



                }

                else if (trip.getTripStatus().isTripStarted()) {

                    statusMessageTextView.setText(trip.getDriver().getUsername() + " is taking you to "+ trip.getAcceptedRequest().getDestinationAddress());

                }

                else if (trip.getTripStatus().isReachedRider()) {

                    statusMessageTextView.setText(trip.getDriver().getUsername() + " has arrived");

                }




            }
        });


    }


//    private void listenForTripStart(){
//        Date date=new Date();
//        Rider.firebaseFirestore.collection("riders").document("rider:" + trip.getRider().getEmail()).collection("trips").document("trip:" + date.getHours() + "-" + date.getDay() + "-" + date.getMonth() + "-" + date.getYear()).addSnapshotListener(new EventListener<DocumentSnapshot>() {
//            @Override
//            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
//                trip = value.toObject(Trip.class);
//
//                if (trip.getTripStatus().isTripStarted()) {
//
//                    statusMessageTextView.setText(trip.getDriver().getUsername() + " is taking you to "+ trip.getAcceptedRequest().getDestinationAddress());
//
//                }
//
//            }
//        });
//
//
//
//
//
//    }

}