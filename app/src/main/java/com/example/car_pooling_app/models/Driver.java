package com.example.car_pooling_app.models;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;

import java.util.Date;

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
            Log.d("here","came here");

            Trip trip= (Trip) object;
            Date date = new Date();

            firebaseFirestore.collection("drivers").document("driver:" + getEmail()).collection("trips").document("trip:" + date.getHours()+"-"+date.getDay()+"-"+date.getMonth()+"-"+date.getYear()).set(trip).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void unused) {
                    Toast.makeText(context, "Heeeeeh",Toast.LENGTH_SHORT);
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(context, "Trip not accepted",Toast.LENGTH_SHORT);
                }
            });

        }}
    @Override
    public void updateData(Object object,OnUpdate onUpdate) {


        if (object instanceof  Trip) {

            Trip trip= (Trip) object;

            Date date = new Date();


            firebaseFirestore.collection("drivers").document("driver:" + getEmail()).collection("trips").document("trip:" + date.getHours() + "-" + date.getDay() + "-" + date.getMonth() + "-" + date.getYear()).set(trip).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void unused) {

                   // Toast.makeText(context,"Driver Arrived to Rider",Toast.LENGTH_SHORT);
                    onUpdate.finishTask();

                }
            });
        }

    }

    @Override
    public void deleteData(Object object) {


        if(object instanceof Request) {

            Request request=(Request)object;

            firebaseFirestore.collection("requests").document("request:"+request.getRider().getEmail()).delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void unused) {
                    Log.d("requestDelete","deleted successfully");


                }
            });
        }

    }
}
