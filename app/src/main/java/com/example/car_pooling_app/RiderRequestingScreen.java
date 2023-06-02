package com.example.car_pooling_app;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Switch;
import android.widget.Toast;

import com.example.car_pooling_app.models.Driver;
import com.example.car_pooling_app.models.Request;
import com.example.car_pooling_app.models.Rider;
import com.example.car_pooling_app.models.Trip;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.gson.Gson;

import java.util.Date;

public class RiderRequestingScreen extends AppCompatActivity {

    Switch destinationSwitch;
    Button findDriverButton;
    EditText homeAddressEditText;
    EditText riderPaymentofferEditText;
    Rider rider;
    Request riderRequest;
    ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rider_requesting_screen);

        findDriverButton = findViewById(R.id.findDriverButton);
        homeAddressEditText = findViewById(R.id.homeAddressEditText);
        destinationSwitch=findViewById(R.id.destinationSwitch);
        riderPaymentofferEditText=findViewById(R.id.paymentOfferEditText);
        progressBar=findViewById(R.id.progressBar);

        progressBar.setVisibility(View.GONE);

        SharedPreferences sPreferences = getSharedPreferences("riderData", Context.MODE_PRIVATE);
        String nameSharedPref = sPreferences.getString("MyObject",null);
        Gson gson = new Gson();
        rider = gson.fromJson(nameSharedPref,Rider.class);

        Log.d("riderinfo",rider.getPhoneNumber());


        findDriverButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                progressBar.setVisibility(View.VISIBLE);
                progressBar.animate();
                findDriverButton.setEnabled(false);
                riderPaymentofferEditText.setEnabled(false);
                homeAddressEditText.setEnabled(false);

                boolean isGoingToAAST=destinationSwitch.isChecked();

                String srcAddress;
                String destAddress;

                if(isGoingToAAST){

                    srcAddress=homeAddressEditText.getText().toString();
                    destAddress="AAST";

                }else{

                    srcAddress="AAST";
                    destAddress=homeAddressEditText.getText().toString();


                }

                double paymentOffer=Double.parseDouble(riderPaymentofferEditText.getText().toString());

               riderRequest=new Request(srcAddress,destAddress,rider,paymentOffer);


              postRiderRequest();
                //listenForTripAcception();

                Log.d("switch", String.valueOf(destinationSwitch.isChecked()));
            }
        });

    }

    private void postRiderRequest() {
        Rider.firebaseFirestore.collection("requests").document("request:"+rider.getEmail()).set(riderRequest).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Toast.makeText(RiderRequestingScreen.this,"Requesting ",Toast.LENGTH_SHORT).show();
                listenForTripAcception();

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(RiderRequestingScreen.this,"Request failed ",Toast.LENGTH_SHORT).show();

            }
        });

    }

    private void listenForTripAcception()
    {
        Date date= new Date();


        Rider.firebaseFirestore.collection("riders").document("rider:" + rider.getEmail()).collection("trips").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot snapshots, @Nullable FirebaseFirestoreException e) {

                Toast.makeText(RiderRequestingScreen.this,"called",Toast.LENGTH_LONG).show();



                if (e != null) {
                    Log.w("TAG", "listen:error", e);
                    return;
                }
                else{

                    if(snapshots != null) {


                        Log.d("isCache", String.valueOf(snapshots.getMetadata().isFromCache()));

                        for (DocumentChange dc : snapshots.getDocumentChanges()) {
                            Trip trip = dc.getDocument().toObject(Trip.class);
                            Log.d("dataa", String.valueOf(trip.getTripStatus().isCompleted()));

                            if(!trip.getTripStatus().isCompleted()){
                                Log.d("yarab", "dirver accepted");
                                Toast.makeText(RiderRequestingScreen.this,"Driver Accepted",Toast.LENGTH_LONG).show();
                                Log.d("DCName",dc.getDocument().getId());

                                progressBar.setVisibility(View.GONE);

                                SharedPreferences sPreferences = getSharedPreferences("sPrefTrip",Context.MODE_PRIVATE);
                                SharedPreferences.Editor editor = sPreferences.edit();
                                Gson gson = new Gson();
                                String json = gson.toJson(trip);
                                //editor.putString("Name","Ahmed El-Hussein");
                                editor.putString("trip", json);
                                editor.apply();


                                Intent intent=new Intent(RiderRequestingScreen.this, RiderTripActivity.class);
                                startActivity(intent);

                                findDriverButton.setEnabled(true);

                                riderPaymentofferEditText.setEnabled(true);
                                homeAddressEditText.setEnabled(true);

                                //requestsAdapter.notifyDataSetChanged();

                            }else{
                                Log.d("Fail", "Failed!!!!");
                            }


                        }
                    }
                }

            }
        });
    }

}