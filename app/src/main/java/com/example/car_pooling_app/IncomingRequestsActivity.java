package com.example.car_pooling_app;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.car_pooling_app.adapters.RequestsAdapter;
import com.example.car_pooling_app.models.Driver;
import com.example.car_pooling_app.models.Request;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.gson.Gson;

import java.util.ArrayList;
//import com.google.gson.Gson;

public class IncomingRequestsActivity extends AppCompatActivity {



    //FirebaseFirestore firebaseFirestore=FirebaseFirestore.getInstance();

    ArrayList<Request> requestArrayList=new ArrayList<>();
    RecyclerView recyclerView;
TextView noRequestsTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_incoming_requests);

        SharedPreferences sPreferences = getSharedPreferences("sPref", Context.MODE_PRIVATE);
        String nameSharedPref = sPreferences.getString("MyObject", null);
        Gson gson = new Gson();
        Driver driver = gson.fromJson(nameSharedPref, Driver.class);
        noRequestsTextView=findViewById(R.id.noRequestView);

        recyclerView = findViewById(R.id.recycleView);
        RequestsAdapter requestsAdapter = new RequestsAdapter(requestArrayList,IncomingRequestsActivity.this,driver,DriverTripActivity.class);
        recyclerView.setAdapter(requestsAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

     //   getIncomingRequests();




        Driver.firebaseFirestore.collection("requests").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot snapshots,
                                @Nullable FirebaseFirestoreException e) {
                if (e != null) {
                    Log.w("TAG", "listen:error", e);
                    return;
                }
                else{
                    requestArrayList.clear();
                    Driver.firebaseFirestore.clearPersistence();




                    if(snapshots != null) {



                        for (DocumentChange dc : snapshots.getDocumentChanges()) {
                            Request request = dc.getDocument().toObject(Request.class);
                            Log.d("dataa", request.getDestinationAddress());
                            requestArrayList.add(request);
                            requestsAdapter.notifyDataSetChanged();//DOne by Al hussein


                        }


                        checkRequests();
                    }
                }
            };
        });




    }

    private void checkRequests(){


        if(requestArrayList.size()>0){
            noRequestsTextView.setVisibility(View.GONE);


        } else{
            noRequestsTextView.setVisibility(View.VISIBLE);
        }
    }

    private void getIncomingRequests(){




        Driver.firebaseFirestore.collection("requests").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot snapshots,
                                @Nullable FirebaseFirestoreException e) {
                if (e != null) {
                    Log.w("TAG", "listen:error", e);
                    return;
                }
                else{

                    if(snapshots != null) {
                        for (DocumentChange dc : snapshots.getDocumentChanges()) {
                            Request request = dc.getDocument().toObject(Request.class);
                            Log.d("dataa", request.getDestinationAddress());
                            requestArrayList.add(request);

                        }
                    }
                }
        };
                });



    }

    private void updateUI(){


    }
//        firebaseFirestore.collection("drivers").document(driverId).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
//            @Override
//            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
//
//                Driver driver=task.getResult().toObject(Driver.class);
//
//                Log.d("driver",  driver.getCar().getPlateNumber());
//
//            }
//        });
//
//
//
//
//    }
//
//
//
//
//    }
}