package com.parking.plana.ctrl;

import com.parking.plana.core.Car;
import com.parking.plana.core.ParkingCard;
import com.parking.plana.view.Viewer;

public class Transformer {
    private Viewer listener;
    public Transformer(Viewer listner) {
        this.listener=listner;
    }

    public Transformer() {

    }

    public Car convertToCar(String id) {
        return new Car(id);
    }

    public ParkingCard convertToParkingCard(String id) {
        return new ParkingCard(id);
    }

    public String convertToCarId(Car car) {
        return car.getId();
    }

    public String convertToParkingCardId(ParkingCard card) {
        return card.getId();
    }
}
