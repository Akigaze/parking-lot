package com.parking.tdd.core;

import com.parking.tdd.core.exception.AllParkingLotsFullException;
import com.parking.tdd.core.exception.InvalidParkingCardException;

import java.util.List;

public class ParkingBoy {
    private List<ParkingLot> parkingLotList;

    public ParkingBoy(List<ParkingLot> parkingLotList) {
        this.parkingLotList=parkingLotList;
    }

    public ParkingCard park(Car car) {
        for (ParkingLot lot:parkingLotList){
            if (!lot.isFull()){
                return lot.park(car);
            }
        }
        throw new AllParkingLotsFullException();
    }

    public Car unPark(ParkingCard card){
        for (ParkingLot lot:parkingLotList){
            if(lot.containsParkingCard(card)){
                return lot.unpark(card);
            }
        }
        throw new InvalidParkingCardException();
    }
}
