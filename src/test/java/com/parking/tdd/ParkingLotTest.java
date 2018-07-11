package com.parking.tdd;

import org.junit.Test;

import static org.junit.jupiter.api.Assertions.fail;

public class ParkingLotTest {
    @Test
    public void should_park_successfully_when_parking_lot_is_not_full(){
        ParkingLot parkingLot=new ParkingLot(2);
        Car car=new Car();
        try {
            parkingLot.park(car);
        }catch (ParkingLotFullException exception){
            fail("The Parking Lot is Full.");
        }
    }
}
