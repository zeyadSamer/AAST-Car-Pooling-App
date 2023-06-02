package com.example.car_pooling_app.models;

public class TripStatus {

    boolean completed;
    boolean reachedRider;
    int rating;
    TripStatus(){

    }

    public TripStatus(boolean completed, int rating, boolean reachedRider) {
        this.completed = completed;
        this.rating = rating;
        this.reachedRider = reachedRider;
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
