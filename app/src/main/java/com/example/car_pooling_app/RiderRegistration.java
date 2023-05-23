package com.example.car_pooling_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class RiderRegistration extends AppCompatActivity {


    EditText usernameEditText;
    EditText emailEditText;
    EditText passwordEditText;
    EditText phoneNumberEditText;
    TextView screenTitle;


    Button registeringButtonStatus;
    Button signButton;
    Boolean isSigningIn=false;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rider_registration);


        usernameEditText=findViewById(R.id.username);
        emailEditText=findViewById(R.id.email);
        passwordEditText=findViewById(R.id.password);

        registeringButtonStatus=findViewById(R.id.registerButtonStatus);
        phoneNumberEditText=findViewById(R.id.phone);
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





}