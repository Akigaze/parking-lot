package com.parking.tdd;

public class ParkingCard {
    private int lotId;

    public ParkingCard(){}
    public ParkingCard(int lotId) {
        this.lotId = lotId;
    }

    public int getLotId() {
        return lotId;
    }
}
