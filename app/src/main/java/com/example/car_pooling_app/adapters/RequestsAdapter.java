package com.example.car_pooling_app.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.car_pooling_app.R;
import com.example.car_pooling_app.models.Request;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class RequestsAdapter extends RecyclerView.Adapter<RequestsAdapter.ViewHolder>{

    final private ArrayList<Request> requests;

    public RequestsAdapter( ArrayList<Request> requests) {

        this.requests = requests;
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

       holder.riderSrcAddress.setText(request.getSrcAddress());
       holder.riderDestinationAddress.setText(request.getDestinationAddress());
       holder.requestPaymentOffer.setText(request.getRiderPaymentOffer().toString());






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


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            this.riderName=itemView.findViewById(R.id.riderName);
            this.riderSrcAddress=itemView.findViewById(R.id.riderLocation);
            this.riderDestinationAddress=itemView.findViewById(R.id.riderDestination);
            this.requestPaymentOffer=itemView.findViewById(R.id.paymentOffer);




        }
    }
}
