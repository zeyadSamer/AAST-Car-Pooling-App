package com.example.car_pooling_app.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.car_pooling_app.R;
import com.example.car_pooling_app.models.Driver;
import com.example.car_pooling_app.models.Request;
import com.example.car_pooling_app.models.Trip;
import com.example.car_pooling_app.models.TripStatus;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class RequestsAdapter extends RecyclerView.Adapter<RequestsAdapter.ViewHolder>{

    final private ArrayList<Request> requests;
    final private Context context;
    final private Driver driver;


    public RequestsAdapter( ArrayList<Request> requests,Context context,Driver driver) {

        this.driver=driver;

        this.requests = requests;
        this.context=context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());

        return new ViewHolder(layoutInflater.inflate(R.layout.request_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

       final Request request= this.requests.get(position);

       holder.riderName.setText(request.getRider().getUsername());

       holder.riderSrcAddress.setText("From: "+request.getSrcAddress());
       holder.riderDestinationAddress.setText("To: "+request.getDestinationAddress());
       holder.requestPaymentOffer.setText("EGP "+request.getRiderPaymentOffer().toString());

       holder.acceptButton.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {

               Toast.makeText(context,"Request Accepted",Toast.LENGTH_SHORT).show();


               Trip trip =new Trip(driver,request.getRider(),request,new TripStatus(false,5));


               request.getRider().addData(context,trip);


             //  Toast.makeText(context,"Request Accepted",Toast.LENGTH_SHORT).show();

           }
       });







    }


    @Override
    public int getItemCount() {
        return requests.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView riderName;
        TextView riderSrcAddress;
        TextView riderDestinationAddress;

        TextView requestPaymentOffer;
        Button acceptButton;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            this.riderName=itemView.findViewById(R.id.riderName);
            this.riderSrcAddress=itemView.findViewById(R.id.riderLocation);
            this.riderDestinationAddress=itemView.findViewById(R.id.riderDestination);
            this.requestPaymentOffer=itemView.findViewById(R.id.paymentOffer);
            this.acceptButton=itemView.findViewById(R.id.acceptOfferButton);




        }
    }
}
