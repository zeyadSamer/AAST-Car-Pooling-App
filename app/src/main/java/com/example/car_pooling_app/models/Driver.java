package com.example.car_pooling_app.models;

import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;

public class Driver extends User{

    private Car car;

    public Driver(){
        super("","","");

    }



    public Driver(String email,String username,String phonenumber,Car car){
        super(username,email,phonenumber);

        this.car=car;

    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    @Override
    public void addData(Context context, Object object) {

        if(object instanceof Trip ) {

            Trip trip= (Trip) object;
            firebaseFirestore.collection("drivers").document("driver:" + getEmail()).collection("trips").add(trip).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                @Override
                public void onSuccess(DocumentReference documentReference) {
                    Toast.makeText(context,"Trip made",Toast.LENGTH_SHORT).show();


                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(context,"Trip not made lelasaf",Toast.LENGTH_SHORT).show();

                }


            });


        }}
    @Override
    public void updateData(Object object) {

    }

    @Override
    public void deleteData(Object object) {

    }
}
