package com.example.car_pooling_app.models;

public class TripStatus {

    boolean completed;
    int rating;
    TripStatus(){

    }


    public TripStatus(boolean completed, int rating) {
        this.completed = completed;
        this.rating = rating;
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
}
