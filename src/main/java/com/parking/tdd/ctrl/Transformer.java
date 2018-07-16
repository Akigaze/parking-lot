package com.parking.tdd.ctrl;

import com.parking.tdd.core.Car;
import com.parking.tdd.core.ParkingCard;

public class Transformer {

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
