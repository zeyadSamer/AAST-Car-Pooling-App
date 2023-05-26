package com.example.car_pooling_app.models;

public class Car {


    private String plateNumber;
    private String carType;

    public Car(String plateNumber,String carType){

        this.plateNumber=plateNumber;
        this.carType=carType;

    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public String getCarType() {
        return carType;
    }

    public void setCarType(String carType) {
        this.carType = carType;
    }
}
