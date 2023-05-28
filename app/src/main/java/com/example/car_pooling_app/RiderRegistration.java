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

import com.example.car_pooling_app.models.Rider;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class RiderRegistration extends AppCompatActivity {


    EditText usernameEditText;
    EditText emailEditText;
    EditText passwordEditText;
    EditText phoneNumberEditText;
    TextView screenTitle;


    Button registeringButtonStatus;
    Button signButton;
    Boolean isSigningIn=false;


    String email;
    String username;
    String password;
    String phone;



    
    
    
    private FirebaseAuth firebaseAuth=FirebaseAuth.getInstance();
    private FirebaseFirestore  firebaseFireStore=FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rider_registration);

        usernameEditText=(EditText) findViewById(R.id.username);
        emailEditText=(EditText) findViewById(R.id.email);
        passwordEditText=(EditText) findViewById(R.id.password);
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


    }


    private void renderRegisterScreen(){
        signButton.setText("Sign Up");
        registeringButtonStatus.setText("Already have an account ? LogIn ");
        screenTitle.setText("Sign Up");
        phoneNumberEditText.setVisibility(View.VISIBLE);
        usernameEditText.setVisibility(View.VISIBLE);


    }


    private void signUp(String email, String password){


        firebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if(task.isSuccessful()) {
                    Toast.makeText(RiderRegistration.this, "Signed Up successfully", Toast.LENGTH_LONG).show();
                    postRiderData();

                }else{
                    Toast.makeText(RiderRegistration.this, "Error in Signing Up", Toast.LENGTH_LONG).show();
                }

            }


        });

    }


    private void logIn(String email,String password){


        firebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()) {
                    Toast.makeText(RiderRegistration.this, "logged in successfully", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(RiderRegistration.this, "Error in Signing in", Toast.LENGTH_LONG).show();
                }

            }
            }

        );




    }



    private void postRiderData(){

        Rider rider= new Rider(email,username,phone);



//ANOTHER SOLUTION
//        firebaseFireStore.collection("riders").document("rider"+firebaseAuth.getCurrentUser().getUid()).set(rider).addOnSuccessListener(new OnSuccessListener<Void>() {
//            @Override
//            public void onSuccess(Void unused) {
//
//                Toast.makeText(RiderRegistration.this,"Storage Succeess",Toast.LENGTH_SHORT).show();
//
//            }
//        }).addOnFailureListener(new OnFailureListener() {
//            @Override
//            public void onFailure(@NonNull Exception e) {
//                Toast.makeText(RiderRegistration.this,e.toString(),Toast.LENGTH_SHORT).show();
//                Log.d("errorsss",e.toString());
//
//
//            }
//        });



        firebaseFireStore.collection("riders").add(rider).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                Toast.makeText(RiderRegistration.this,"Storage Succeess" ,Toast.LENGTH_SHORT).show();
//

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(RiderRegistration.this,"Error in saving data",Toast.LENGTH_SHORT).show();
//
            }
        });
    }





}