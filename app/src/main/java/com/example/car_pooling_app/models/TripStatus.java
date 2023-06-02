package com.example.car_pooling_app.models;

public class TripStatus {

   private boolean completed;
   private boolean reachedRider;
   private boolean tripStarted;

    private int riderRating;
    private int driverRating;

    TripStatus(){

    }

    public TripStatus(boolean completed, boolean reachedRider,boolean tripStarted,int riderRating,int driverRating) {
        this.completed = completed;
        this.riderRating=riderRating;
        this.driverRating=driverRating;

        this.reachedRider = reachedRider;

        this.tripStarted=tripStarted;
    }


    public int getRiderRating() {
        return riderRating;
    }

    public void setRiderRating(int riderRating) {
        this.riderRating = riderRating;
    }

    public int getDriverRating() {
        return driverRating;
    }

    public void setDriverRating(int driverRating) {
        this.driverRating = driverRating;
    }

    public boolean isTripStarted() {
        return tripStarted;
    }

    public void setTripStarted(boolean tripStarted) {
        this.tripStarted = tripStarted;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public boolean isReachedRider() { return reachedRider; }

    public void setReachedRider(boolean reachedRider) { this.reachedRider = reachedRider;}
}
