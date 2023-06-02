package com.example.car_pooling_app.models;

public class TripStatus {

   private boolean completed;
   private boolean reachedRider;
   private boolean tripStarted;

    int rating;
    TripStatus(){

    }

    public TripStatus(boolean completed, int rating, boolean reachedRider,boolean tripStarted) {
        this.completed = completed;
        this.rating = rating;
        this.reachedRider = reachedRider;

        this.tripStarted=tripStarted;
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

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public boolean isReachedRider() { return reachedRider; }

    public void setReachedRider(boolean reachedRider) { this.reachedRider = reachedRider;}
}
