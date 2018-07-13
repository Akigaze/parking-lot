package com.parking.tdd.core;

import com.parking.tdd.core.Car;
import com.parking.tdd.core.ParkingCard;
import com.parking.tdd.core.ParkingLot;
import com.parking.tdd.core.ParkingLotFullException;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.fail;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.mockito.Mockito.mock;

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

    @Test
    public void should_get_a_car_when_give_a_wrong_card(){
        ParkingLot parkingLot=new ParkingLot(2);
        Car car=new Car();
        ParkingCard card=parkingLot.park(car);

        ParkingCard anotherCard=new ParkingCard();

        assertThat(parkingLot.unpark(anotherCard), not(car));
    }

    @Test
    public void should_be_true_call_isFull_when_parking_lot_is_full(){
        ParkingLot parkingLot=new ParkingLot(1);
        Car car=new Car();
        parkingLot.park(car);

        boolean result=parkingLot.isFull();

        assertThat(result, is(true));
    }

    @Test
    public void should_be_false_call_isFull_when_parking_lot_is_not_full(){
        ParkingLot parkingLot=new ParkingLot(1);

        boolean result=parkingLot.isFull();

        assertThat(result, is(false));
    }
    @Test
    public void should_be_false_call_isFull_after_take_out_a_car(){
        ParkingLot parkingLot=new ParkingLot(1);
        ParkingCard card=parkingLot.park(new Car());
        parkingLot.unpark(card);

        boolean result=parkingLot.isFull();

        assertThat(result, is(false));
    }

    @Test
    public void should_a_car_be_parked_again_successfully_after_taking_out(){
        ParkingLot parkingLot=new ParkingLot(1);
        Car car=new Car();
        ParkingCard card=parkingLot.park(car);

        parkingLot.unpark(card);

        try {
            parkingLot.park(car);
        }catch (ParkingLotFullException exception){
            fail("Can't Park Again.");
        }

    }

    @Test
    public void should_return_false_when_two_Parking_Cards_with_different_id(){
        //give
        ParkingCard card1=new ParkingCard();
        ParkingCard card2=new ParkingCard();
        //when
        card2.setID(card1.getId());
        ParkingCard card3=new ParkingCard();

        //then
        assertThat(card1.equals(card2),is(true));
        assertThat(card1.equals(card3),is(false));

    }

}
