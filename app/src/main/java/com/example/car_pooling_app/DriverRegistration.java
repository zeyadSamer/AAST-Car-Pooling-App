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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

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
                String email=emailEditText.getText().toString();
                String username=usernameEditText.getText().toString();
                String password=passwordEditText.getText().toString();
                String phone=phoneNumberEditText.getText().toString();
                String carType=carTypeEditText.getText().toString();
                String plateNumbet=plateNumberEditText.getText().toString();

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








}