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

    @Test
    public void should_park_successfully_the_second_parking_lot_is_not_full(){
        List<ParkingLot> parkingLotList=new ArrayList<>();
        parkingLotList.add(new ParkingLot(0));
        parkingLotList.add(new ParkingLot(2));

        ParkingBoy boy=new ParkingBoy(parkingLotList);
        Car car=new Car();
        try{
            boy.park(car);
        }catch (AllParkingLotsFullException exception){
            fail("All My Parking Lots Are Full!");
        }
    }

    @Test
    public void should_park_failly_when_all_my_parking_lot_is_full(){
        List<ParkingLot> parkingLotList=new ArrayList<>();
        parkingLotList.add(new ParkingLot(0));
        parkingLotList.add(new ParkingLot(1));

        ParkingBoy boy=new ParkingBoy(parkingLotList);
        Car car=new Car();
        boy.park(car);

        try{
            boy.park(car);
            fail("All My Parking Lots Are Full!");
        }catch (AllParkingLotsFullException exception){

        }
    }
}
