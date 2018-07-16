package com.parking.planb.ctrl;

import com.parking.planb.core.Car;
import com.parking.planb.view.Viewer;
import com.parking.planb.core.ParkingCard;

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
