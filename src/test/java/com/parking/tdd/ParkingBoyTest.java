package com.parking.tdd;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.fail;

public class ParkingBoyTest {
    @Test
    public void should_park_successfully_when_no_cars_in_my_parking_lots(){
        List<ParkingLot> parkingLotList=new ArrayList<>();
        parkingLotList.add(new ParkingLot(2));
        ParkingBoy boy=new ParkingBoy(parkingLotList);
        Car car=new Car();
        try{
            boy.park(car);
        }catch (AllParkingLotsFullException exception){
            fail("All My Parking Lots Are Full!");
        }
    }
}
