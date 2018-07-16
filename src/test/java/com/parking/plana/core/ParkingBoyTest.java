package com.parking.plana.core;

import com.parking.plana.core.exception.AllParkingLotsFullException;
import com.parking.plana.core.exception.InvalidParkingCardException;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.fail;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ParkingBoyTest {
    @Test
    public void should_park_successfully_when_no_cars_in_my_parking_lots(){
        List<ParkingLot> parkingLotList=new ArrayList<>();
        ParkingLot lot=mock(ParkingLot.class);

        parkingLotList.add(lot);
        ParkingBoy boy=new ParkingBoy(parkingLotList);
        Car car=new Car();
        when(lot.isFull()).thenReturn(false);

        try{
            boy.park(car);
            verify(lot).park(car);
        }catch (AllParkingLotsFullException exception){
            fail("All My Parking Lots Are Full!");
        }
    }

    @Test
    public void should_park_successfully_the_second_parking_lot_is_not_full(){
        List<ParkingLot> parkingLotList=new ArrayList<>();
        ParkingLot lot1=mock(ParkingLot.class);
        ParkingLot lot2=mock(ParkingLot.class);

        parkingLotList.add(lot1);
        parkingLotList.add(lot2);

        ParkingBoy boy=new ParkingBoy(parkingLotList);
        Car car=new Car();
        when(lot1.isFull()).thenReturn(true);
        when(lot2.isFull()).thenReturn(false);

        try{
            boy.park(car);
            verify(lot2).park(car);

        }catch (AllParkingLotsFullException exception){
            fail("All My Parking Lots Are Full!");
        }
    }

    @Test
    public void should_park_failly_when_all_my_parking_lot_is_full(){
        List<ParkingLot> parkingLotList=new ArrayList<>();
        ParkingLot lot1=mock(ParkingLot.class);
        ParkingLot lot2=mock(ParkingLot.class);

        parkingLotList.add(lot1);
        parkingLotList.add(lot2);

        ParkingBoy boy=new ParkingBoy(parkingLotList);
        Car car=new Car();
        when(lot1.isFull()).thenReturn(true);
        when(lot2.isFull()).thenReturn(false,true);

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
        ParkingLot lot1=mock(ParkingLot.class);
        parkingLotList.add(lot1);

        ParkingBoy boy=new ParkingBoy(parkingLotList);
        Car car=new Car();
        when(lot1.isFull()).thenReturn(false);
        ParkingCard card=boy.park(car);

        when(lot1.containsParkingCard(card)).thenReturn(true);
        when(lot1.unpark(card)).thenReturn(car);

        assertThat(boy.unPark(card),is(car));
        verify(lot1).unpark(card);
    }

    @Test
    public void should_get_the_specific_car_from_many_cars_in_more_than_a_parking_lots_by_call_unPark_when_give_a_right_parking_card(){
        List<ParkingLot> parkingLotList=new ArrayList<>();
        ParkingLot lot1=mock(ParkingLot.class);
        ParkingLot lot2=mock(ParkingLot.class);

        parkingLotList.add(lot1);
        parkingLotList.add(lot2);
        ParkingBoy boy=new ParkingBoy(parkingLotList);

        Car car1=new Car();
        Car car2=new Car();
        when(lot1.isFull()).thenReturn(false,true);
        when(lot2.isFull()).thenReturn(false);

        ParkingCard card1=new ParkingCard();
        ParkingCard card2=new ParkingCard();
        when(lot1.unpark(card1)).thenReturn(car1);
        when(lot2.unpark(card2)).thenReturn(car2);

        boy.park(car1);
        boy.park(car2);
        when(lot1.containsParkingCard(card1)).thenReturn(true);
        when(lot2.containsParkingCard(card2)).thenReturn(true);

        when(lot1.unpark(card1)).thenReturn(car1);
        when(lot2.unpark(card2)).thenReturn(car2);

        assertThat(boy.unPark(card1),is(car1));
        assertThat(boy.unPark(card2),not(car1));
    }

    @Test
    public void should_park_by_order_when_call_park(){
        List<ParkingLot> parkingLotList=new ArrayList<>();
        ParkingLot lot1=mock(ParkingLot.class);
        ParkingLot lot2=mock(ParkingLot.class);

        parkingLotList.add(lot1);
        parkingLotList.add(lot2);
        ParkingBoy boy=new ParkingBoy(parkingLotList);

        Car car1=new Car();
        Car car2=new Car();
        when(lot1.isFull()).thenReturn(false,true);
        when(lot2.isFull()).thenReturn(false);
        boy.park(car1);
        verify(lot1).park(car1);
        boy.park(car2);
        verify(lot2).park(car2);


//        assertThat(lot1.getParkedCars().containsValue(car1),is(true));
//        assertThat(lot2.getParkedCars().containsValue(car2),is(true));
    }

    @Test
    public void should_park_the_car_to_lot1_by_call_park_when_lot2_is_full_while_lot1_is_not_full(){
        List<ParkingLot> parkingLotList=new ArrayList<>();
        ParkingLot lot1=mock(ParkingLot.class);
        ParkingLot lot2=mock(ParkingLot.class);

        parkingLotList.add(lot1);
        parkingLotList.add(lot2);
        ParkingBoy boy=new ParkingBoy(parkingLotList);

        Car car1=new Car();
        Car car2=new Car();
        Car car3=new Car();

        when(lot1.isFull()).thenReturn(false,true,false);
        when(lot2.isFull()).thenReturn(false,true);
        ParkingCard card=new ParkingCard();
        when(lot1.park(car1)).thenReturn(card);
        boy.park(car1);
        boy.park(car2);
        when(lot1.containsParkingCard(card)).thenReturn(true);
        boy.unPark(card);

        boy.park(car3);
        verify(lot1).park(car1);

//        assertThat(lot1.getParkedCars().containsValue(car3),is(true));
//        assertThat(lot2.getParkedCars().containsValue(car2),is(true));
    }

    @Test
    public void should_be_fail_by_call_unPark_when_give_a_invalid_parking_card(){
        List<ParkingLot> parkingLotList=new ArrayList<>();
        ParkingLot lot1=mock(ParkingLot.class);
        parkingLotList.add(lot1);
        ParkingBoy boy=new ParkingBoy(parkingLotList);
        Car car1=new Car();
        when(lot1.isFull()).thenReturn(false);

        boy.park(car1);

        try {
            boy.unPark(new ParkingCard());
            fail("The Parking Card Is Invalid!");
        }catch (InvalidParkingCardException exception){

        }

    }
}
