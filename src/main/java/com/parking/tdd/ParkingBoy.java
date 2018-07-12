package com.parking.tdd;

import java.util.List;

public class ParkingBoy {
    private List<ParkingLot> parkingLotList;

    public ParkingBoy(List<ParkingLot> parkingLotList) {
        this.parkingLotList=parkingLotList;
    }

    public ParkingCard park(Car car) throws AllParkingLotsFullException {
        parkingLotList.get(0).park(car);
        return null;
    }
}
