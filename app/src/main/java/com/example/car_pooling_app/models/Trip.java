package com.example.car_pooling_app.models;

public class Trip {

    Driver driver;
    Rider rider;
    Request acceptedRequest;
    TripStatus tripStatus;

    Trip(){


    }

    public Trip(Driver driver, Rider rider, Request acceptedRequest, TripStatus tripStatus) {
        this.driver = driver;
        this.rider = rider;
        this.acceptedRequest = acceptedRequest;
        this.tripStatus = tripStatus;
    }

    public TripStatus getTripStatus() {
        return tripStatus;
    }

    public void setTripStatus(TripStatus tripStatus) {
        this.tripStatus = tripStatus;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public Rider getRider() {
        return rider;
    }

    public void setRider(Rider rider) {
        this.rider = rider;
    }

    public Request getAcceptedRequest() {
        return acceptedRequest;
    }

    public void setAcceptedRequest(Request acceptedRequest) {
        this.acceptedRequest = acceptedRequest;
    }


}
