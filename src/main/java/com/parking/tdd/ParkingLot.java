package com.parking.tdd;

public class ParkingLot {
    private int capacity;
    private int emptySiteNum;
    public ParkingLot(int capacity) {
        this.capacity=capacity;
        this.emptySiteNum=capacity;
    }

    public void park(Car car) {
        emptySiteNum--;
        if (emptySiteNum==0){
            throw new ParkingLotFullException();
        }
    }
}
