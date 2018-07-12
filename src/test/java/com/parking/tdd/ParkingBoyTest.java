package com.parking.tdd;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.fail;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;

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

    @Test
    public void should_get_a_car_by_call_unPark_when_give_a_right_parking_card(){
        List<ParkingLot> parkingLotList=new ArrayList<>();
        parkingLotList.add(new ParkingLot(1));

        ParkingBoy boy=new ParkingBoy(parkingLotList);
        Car car=new Car();
        ParkingCard card=boy.park(car);

        assertThat(boy.unParking(card),is(car));
    }

    @Test
    public void should_get_the_specific_car_from_many_cars_in_more_than_a_parking_lots_by_call_unPark_when_give_a_right_parking_card(){
        List<ParkingLot> parkingLotList=new ArrayList<>();
        parkingLotList.add(new ParkingLot(1,1));
        parkingLotList.add(new ParkingLot(2,2));
        ParkingBoy boy=new ParkingBoy(parkingLotList);

        Car car1=new Car();
        Car car2=new Car();
        ParkingCard card1=boy.park(car1);
        ParkingCard card2=boy.park(car2);

        assertThat(boy.unParking(card1),is(car1));
        assertThat(boy.unParking(card2),is(car2));

    }

    @Test
    public void should_failly__by_call_unPark_when_give_a_wrong_parking_card(){
        List<ParkingLot> parkingLotList=new ArrayList<>();
        parkingLotList.add(new ParkingLot(1,1));
        parkingLotList.add(new ParkingLot(2,2));
        ParkingBoy boy=new ParkingBoy(parkingLotList);

        Car car1=new Car();
        Car car2=new Car();
        ParkingCard card1=boy.park(car1);
        ParkingCard card2=boy.park(car2);

        assertThat(boy.unParking(card2),not(car1));
        assertThat(boy.unParking(card1),not(car2));

    }
}
