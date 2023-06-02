package com.example.car_pooling_app.models;

import android.app.Activity;
import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.car_pooling_app.RiderRegistration;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.Exclude;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreSettings;
import com.google.firebase.firestore.MemoryCacheSettings;
import com.google.firebase.firestore.PersistentCacheSettings;

import java.util.function.Function;

import javax.security.auth.callback.Callback;

public abstract class User {

   @Exclude public static FirebaseAuth firebaseAuth=FirebaseAuth.getInstance();
   @Exclude public static FirebaseFirestore firebaseFirestore;
    private String username;
    private String email;

    private String phoneNumber;

    public User(String username, String email,String phoneNumber) {

        this.username = username;
        this.email = email;

        this.phoneNumber = phoneNumber;

    }

    public static void initializeFireBase()
    {
        FirebaseFirestoreSettings settings=new FirebaseFirestoreSettings.Builder().setPersistenceEnabled(false).build();
        firebaseFirestore=FirebaseFirestore.getInstance();
        firebaseFirestore.setFirestoreSettings(settings);
    }


    public  void login(String email,String password, Context context,UserData userData){
        firebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
          @Override
          public void onComplete(@NonNull Task<AuthResult> task) {
              if(task.isSuccessful()) {
                  Toast.makeText(context ,"logged in successfully", Toast.LENGTH_LONG).show();
                  userData.getUserData();


              }else{
                  Toast.makeText(context, "Error in Signing in", Toast.LENGTH_LONG).show();
              }

          }
      }

        );
    }




    public abstract void addData(Context context,Object object);


    public abstract void updateData(Object object,OnUpdate onUpdate);
    public abstract void deleteData(Object object);



    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }



    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
