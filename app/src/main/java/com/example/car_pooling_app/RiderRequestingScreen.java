package com.example.car_pooling_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import com.example.car_pooling_app.models.Driver;
import com.example.car_pooling_app.models.Request;
import com.example.car_pooling_app.models.Rider;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.gson.Gson;

public class RiderRequestingScreen extends AppCompatActivity {



    Switch destinationSwitch;
    Button findDriverButton;
    EditText homeAddressEditText;
    EditText riderPaymentofferEditText;
    Rider rider;
    Request riderRequest;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rider_requesting_screen);

        findDriverButton = findViewById(R.id.findDriverButton);
        homeAddressEditText = findViewById(R.id.homeAddressEditText);
        destinationSwitch=findViewById(R.id.destinationSwitch);
        riderPaymentofferEditText=findViewById(R.id.paymentOfferEditText);

        SharedPreferences sPreferences = getSharedPreferences("riderData", Context.MODE_PRIVATE);
        String nameSharedPref = sPreferences.getString("MyObject",null);
        Gson gson = new Gson();
        rider = gson.fromJson(nameSharedPref,Rider.class);

        Log.d("riderinfo",rider.getPhoneNumber());




        findDriverButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

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






                Log.d("switch", String.valueOf(destinationSwitch.isChecked()));
            }
        });

    }

    private void postRiderRequest() {


        Rider.firebaseFirestore.collection("requests").document("request:"+rider.getEmail()).set(riderRequest).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Toast.makeText(RiderRequestingScreen.this,"Requesting ",Toast.LENGTH_SHORT).show();

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(RiderRequestingScreen.this,"Request failed ",Toast.LENGTH_SHORT).show();

            }
        });

    }




}