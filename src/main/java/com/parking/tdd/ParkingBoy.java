package com.parking.tdd;

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

    public Car unPark(ParkingCard card) {
        for (ParkingLot lot:parkingLotList){
            if(lot.getParkedCars().containsKey(card)){
                return lot.unpark(card);
            }
        }
        return null;
    }
}
