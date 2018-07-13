package com.parking.tdd.core;

import com.parking.tdd.core.*;
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

        assertThat(boy.unPark(card),is(car));

    }

    @Test
    public void should_get_the_specific_car_from_many_cars_in_more_than_a_parking_lots_by_call_unPark_when_give_a_right_parking_card(){
        List<ParkingLot> parkingLotList=new ArrayList<>();
        parkingLotList.add(new ParkingLot(1));
        parkingLotList.add(new ParkingLot(2));
        ParkingBoy boy=new ParkingBoy(parkingLotList);

        Car car1=new Car();
        Car car2=new Car();
        ParkingCard card1=boy.park(car1);
        ParkingCard card2=boy.park(car2);

        assertThat(boy.unPark(card1),is(car1));
        assertThat(boy.unPark(card2),is(car2));


    }

    @Test
    public void should_be_a_different_car__by_call_unPark_when_give_a_wrong_parking_card(){
        List<ParkingLot> parkingLotList=new ArrayList<>();
        parkingLotList.add(new ParkingLot(1));
        parkingLotList.add(new ParkingLot(2));
        ParkingBoy boy=new ParkingBoy(parkingLotList);

        Car car1=new Car();
        Car car2=new Car();
        ParkingCard card1=boy.park(car1);
        ParkingCard card2=boy.park(car2);

        assertThat(boy.unPark(card2),not(car1));
        assertThat(boy.unPark(card1),not(car2));

    }

    @Test
    public void should_be_successful_call_park_again_when_take_out_a_car(){
        List<ParkingLot> parkingLotList=new ArrayList<>();
        parkingLotList.add(new ParkingLot(1));
        parkingLotList.add(new ParkingLot(2));
        ParkingBoy boy=new ParkingBoy(parkingLotList);

        Car car1=new Car();
        Car car2=new Car();
        ParkingCard card1=boy.park(car1);

        boy.unPark(card1);

        try {
            boy.park(car2);
        }catch (AllParkingLotsFullException exception){
            fail("All My Parking Lots Are Full!");
        }
    }

    @Test
    public void should_park_by_order_when_call_park(){
        List<ParkingLot> parkingLotList=new ArrayList<>();
        ParkingLot lot1=new ParkingLot(1);
        ParkingLot lot2=new ParkingLot(1);
        parkingLotList.add(lot1);
        parkingLotList.add(lot2);
        ParkingBoy boy=new ParkingBoy(parkingLotList);

        Car car1=new Car();
        Car car2=new Car();
        ParkingCard card1=boy.park(car1);
        ParkingCard card2=boy.park(car2);

        assertThat(lot1.getParkedCars().containsValue(car1),is(true));
        assertThat(lot2.getParkedCars().containsValue(car2),is(true));
    }

    @Test
    public void should_park_the_car_to_lot1_by_call_park_when_lot2_is_full_while_lot1_is_not_full(){
        List<ParkingLot> parkingLotList=new ArrayList<>();
        ParkingLot lot1=new ParkingLot(1);
        ParkingLot lot2=new ParkingLot(1);
        parkingLotList.add(lot1);
        parkingLotList.add(lot2);
        ParkingBoy boy=new ParkingBoy(parkingLotList);

        Car car1=new Car();
        Car car2=new Car();
        Car car3=new Car();
        ParkingCard card1=boy.park(car1);
        boy.park(car2);

        try {
            boy.unPark(card1);
        } catch (InvalidParkingCardException e) {

        }
        boy.park(car3);

        assertThat(lot1.getParkedCars().containsValue(car3),is(true));
        assertThat(lot2.getParkedCars().containsValue(car2),is(true));
    }

    @Test
    public void should_be_fail_by_call_unPark_when_give_a_invalid_parking_card(){
        List<ParkingLot> parkingLotList=new ArrayList<>();
        ParkingLot lot1=new ParkingLot(1);
        ParkingLot lot2=new ParkingLot(1);
        parkingLotList.add(lot1);
        parkingLotList.add(lot2);
        ParkingBoy boy=new ParkingBoy(parkingLotList);

        Car car1=new Car();
        Car car2=new Car();
        boy.park(car1);
        boy.park(car2);

        try {
            boy.unPark(new ParkingCard());
            fail("The Parking Card Is Invalid!");
        }catch (InvalidParkingCardException exception){

        }

    }
}
