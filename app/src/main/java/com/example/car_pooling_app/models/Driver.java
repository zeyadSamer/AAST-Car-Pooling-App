package com.example.car_pooling_app.models;

public class Driver extends User{

    private Car car;



    public Driver(String email,String username,String phonenumber,Car car){
        super(username,email,phonenumber);

        this.car=car;

    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }
}
