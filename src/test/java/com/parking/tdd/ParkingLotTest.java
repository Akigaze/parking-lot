package com.parking.tdd;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;

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

    @Test
    public void should_park_failly_when_parking_lot_is_full(){
        ParkingLot parkingLot=new ParkingLot(1);
        parkingLot.park(new Car());

        Car car=new Car();
        try {
            parkingLot.park(car);
            fail("The Parking Lot is Full.");
        }catch (ParkingLotFullException exception){

        }
    }

    @Test
    public void should_get_a_car_when_give_a_right_card(){
        ParkingLot parkingLot=new ParkingLot(2);
        Car car1=new Car();
        ParkingCard card=parkingLot.park(car1);

        Car car=parkingLot.unpark(card);

        Assertions.assertEquals(car1,car);
    }
}
