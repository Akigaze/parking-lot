package com.parking.tdd;

import java.util.HashMap;
import java.util.Map;

public class ParkingLot {
    private int capacity;
    private int emptySiteNum;
    private Map<ParkingCard,Car> parkedCars=new HashMap<>();
    public ParkingLot(int capacity) {
        this.capacity=capacity;
        this.emptySiteNum=capacity;
    }

    public ParkingCard park(Car car) {
        if (emptySiteNum==0){
            throw new ParkingLotFullException();
        }else {
            ParkingCard card=new ParkingCard();
            parkedCars.put(card,car);
            emptySiteNum--;
            return card;
        }
    }

    public Car unpark(ParkingCard card) {
        return parkedCars.get(card);
    }

    public boolean isFull() {
        return parkedCars.size()==capacity;
    }
}
