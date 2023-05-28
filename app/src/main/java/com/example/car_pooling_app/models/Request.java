package com.example.car_pooling_app.models;



public class Request {


    private String srcAddress;
    private String destinationAddress;
    private Rider rider;

    private Double riderPaymentOffer;



    Request(){

    }

    public Request(String srcAddress, String destinationAddress, Rider rider, double riderPaymentOffer) {
        this.srcAddress = srcAddress;
        this.destinationAddress = destinationAddress;
        this.rider = rider;
        this.riderPaymentOffer = riderPaymentOffer;
    }

    public String getSrcAddress() {
        return srcAddress;
    }

    public void setSrcAddress(String srcAddress) {
        this.srcAddress = srcAddress;
    }

    public String getDestinationAddress() {
        return destinationAddress;
    }

    public void setDestinationAddress(String destinationAddress) {
        this.destinationAddress = destinationAddress;
    }

    public Rider getRider() {
        return rider;
    }

    public void setRider(Rider rider) {
        this.rider = rider;
    }

    public Double getRiderPaymentOffer() {
        return riderPaymentOffer;
    }

    public void setRiderPaymentOffer(Double riderPaymentOffer) {
        this.riderPaymentOffer = riderPaymentOffer;
    }


}
