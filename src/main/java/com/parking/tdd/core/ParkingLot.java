package com.parking.tdd.core;

import com.parking.tdd.core.exception.ParkingLotFullException;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class ParkingLot {
    private int capacity;
    private Map<ParkingCard,Car> parkedCars=new HashMap<>();

    public ParkingLot(int capacity) {
        this.capacity = capacity;
    }

    public Map<ParkingCard,Car> getParkedCars() {
        return this.parkedCars;
    }

    public ParkingCard park(Car car) {
        if (parkedCars.size()<capacity){
            ParkingCard card=new ParkingCard();
            parkedCars.put(card,car);
            return card;
        }else {
            throw new ParkingLotFullException();
        }
    }

    public Car unpark(ParkingCard card) {
        for (ParkingCard c:parkedCars.keySet()){
            if (c.equals(card)){
                return parkedCars.remove(c);
            }
        }
        return null;
    }

    public boolean isFull() {
        return parkedCars.size()==capacity;
    }

    public boolean containsParkingCard(ParkingCard card){
        for (ParkingCard c:parkedCars.keySet()){
            if (c.equals(card)){
                return true;
            }
        }
        return false;
    }

}
