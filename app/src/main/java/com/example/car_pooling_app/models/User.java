package com.example.car_pooling_app.models;

public abstract class User {
    private String username;
    private String email;

    private String phoneNumber;

    public User(String username, String email,String phoneNumber) {
        this.username = username;
        this.email = email;

        this.phoneNumber = phoneNumber;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }



    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
