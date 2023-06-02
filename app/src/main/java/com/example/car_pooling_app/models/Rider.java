package com.example.car_pooling_app.models;

import android.app.Activity;
import android.text.format.Time;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

import java.util.Date;

import android.content.Context;

public class Rider extends User{



//    private String address;

public Rider(){
    super("","","");

}


    public Rider(String email,String username,String phoneNumber){
        super(username,email,phoneNumber);
    }

    @Override
    public void addData(Context context, Object object) {

    if(object instanceof Trip ) {
        Log.d("here","came here");

        Trip trip= (Trip) object;
        Date date= new Date();

            firebaseFirestore.collection("riders").document("rider:" + getEmail()).collection("trips").document("trip:"+date.getHours()+"-"+date.getDay()+"-"+date.getMonth()+"-"+date.getYear()).set(trip).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void unused) {
                    Toast.makeText(context,"heeeehhh",Toast.LENGTH_SHORT);

                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(context,"Trip not accepted",Toast.LENGTH_SHORT);


                }
            });
    }

    }

    @Override
    public void updateData(Object object) {

    if (object instanceof  Trip) {

        Trip trip= (Trip) object;

        Date date = new Date();


        firebaseFirestore.collection("riders").document("rider:" + getEmail()).collection("trips").document("trip:" + date.getHours() + "-" + date.getDay() + "-" + date.getMonth() + "-" + date.getYear()).set(trip).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {

               // Toast.makeText((Context) activity,"Driver Arrived to Rider",Toast.LENGTH_SHORT);


            }
        });
    }

    }

    @Override
    public void deleteData(Object object) {

    }

//    public String getAddress() {
//        return address;
//    }
//
//    public void setAddress(String address) {
//        this.address = address;
//    }


}
