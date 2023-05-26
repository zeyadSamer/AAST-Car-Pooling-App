package com.example.car_pooling_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.car_pooling_app.models.Car;
import com.example.car_pooling_app.models.Driver;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class DriverRegistration extends AppCompatActivity {


    EditText usernameEditText;
    EditText emailEditText;
    EditText passwordEditText;
    TextView screenTitle;
    EditText plateNumberEditText;
    EditText carTypeEditText;
    EditText phoneNumberEditText;


    Button registeringButtonStatus;
    Button signButton;
    Boolean isSigningIn=false;
    FirebaseAuth firebaseAuth=FirebaseAuth.getInstance();
    String email;
    String username;
    String password;
    String phone;
    String carType;
    String plateNumber;
    FirebaseFirestore firebaseFirestore= FirebaseFirestore.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_registration);
        usernameEditText=(EditText) findViewById(R.id.username);
        emailEditText=(EditText) findViewById(R.id.email);
        passwordEditText=(EditText) findViewById(R.id.password);
        plateNumberEditText=(EditText) findViewById(R.id.plateNumber);
        carTypeEditText=(EditText) findViewById(R.id.carType);
        registeringButtonStatus=findViewById(R.id.registerButtonStatus);
        phoneNumberEditText=(EditText) findViewById(R.id.phone);
        screenTitle=findViewById(R.id.screenTitle);


        signButton=findViewById(R.id.signButton);



        registeringButtonStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isSigningIn=!isSigningIn;

                if(isSigningIn){
                    renderLoginScreen();
                }else{
                    renderRegisterScreen();

                }
            }
        });


        signButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 email=emailEditText.getText().toString();
                 username=usernameEditText.getText().toString();
                 password=passwordEditText.getText().toString();
                 phone=phoneNumberEditText.getText().toString();
                 carType=carTypeEditText.getText().toString();
                 plateNumber=plateNumberEditText.getText().toString();

                if (isSigningIn) {

                    Log.d("debugging", "email "+email + password);

                    logIn(email, password);
                }else {
                    signUp(email,password);
                }





            }
        });





    }



    private void renderLoginScreen(){
        signButton.setText("Sign In");
        registeringButtonStatus.setText("Don't have an account ? Sign Up");
        screenTitle.setText("Sign In");

        phoneNumberEditText.setVisibility(View.GONE);
        usernameEditText.setVisibility(View.GONE);

        carTypeEditText.setVisibility(View.GONE);
        plateNumberEditText.setVisibility(View.GONE);




    }



    private void renderRegisterScreen(){
        signButton.setText("Sign Up");
        registeringButtonStatus.setText("Already have an account ? LogIn ");
        screenTitle.setText("Sign Up");
        phoneNumberEditText.setVisibility(View.VISIBLE);
        usernameEditText.setVisibility(View.VISIBLE);
        carTypeEditText.setVisibility(View.VISIBLE);
        plateNumberEditText.setVisibility(View.VISIBLE);



    }



    private void signUp(String email, String password){
        firebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if(task.isSuccessful()) {
                    Toast.makeText(DriverRegistration.this, "Signed Up successfully", Toast.LENGTH_LONG).show();

                    postDriverData();



                }else{
                    Toast.makeText(DriverRegistration.this, "Error in Signing Up", Toast.LENGTH_LONG).show();
                }

            }



        });

    }


    private void logIn(String email,String password){


        firebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
          @Override
          public void onComplete(@NonNull Task<AuthResult> task) {
              if(task.isSuccessful()) {
                  Toast.makeText(DriverRegistration.this, "logged in successfully", Toast.LENGTH_LONG).show();
              }else{
                  Toast.makeText(DriverRegistration.this, "Error in Signing in", Toast.LENGTH_LONG).show();
              }

          }
      }

        );




    }



    private void postDriverData(){

        Car driverCar=new Car(plateNumber,carType);

        Driver driver=new Driver(email,username,phone,driverCar);

        firebaseFirestore.collection("drivers").add(driver).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {

                Toast.makeText(DriverRegistration.this,"Data uploaded Successfully",Toast.LENGTH_SHORT).show();

                //after we succeded in uploading data lets upload driver car
                documentReference.update("car",driverCar).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(DriverRegistration.this,"Car uploaded Successfully",Toast.LENGTH_SHORT).show();

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(DriverRegistration.this,"Car uploading failed",Toast.LENGTH_SHORT).show();

                    }
                });
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(DriverRegistration.this,"Error uploading Data",Toast.LENGTH_SHORT).show();


            }
        });











    }








}