package com.parking.plana.core;

public class Car {
    private String id=String.valueOf(Math.random());

    public Car(){

    }
    public Car(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Car){
            final Car car=(Car)obj;
            if (this.id.equals(car.id)){
                return true;
            }
        }
        return false;
    }
}
