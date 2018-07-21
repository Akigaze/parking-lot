package com.parking.tdd.core;


import com.parking.tdd.core.exception.AllParkingLotsFullException;
import com.parking.tdd.core.exception.DeleteParkingLotWithCarException;
import com.parking.tdd.core.exception.InvalidParkingCardException;
import com.parking.tdd.core.exception.NotExitParkingLotException;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.*;

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

    @Test
    public void should_show_message_of_all_the_parking_lots_when_call_getParkingLotsInfo(){
        //give;
        List<ParkingLot> lotList=new ArrayList<>();
        ParkingLot lot1=mock(ParkingLot.class);
        ParkingLot lot2=mock(ParkingLot.class);
        lotList.add(lot1);
        lotList.add(lot2);

        ParkingBoy boy=new ParkingBoy(lotList);
        String except="|停车场ID|名称|车位|已停车辆|剩余车位|\n" +
                "======================================\n" +
                "|001|软件园停车场|2(个)|2(辆)|0(个)|\n" +
                "|002|唐家湾停车场|2(个)|1(辆)|1(个)|\n" +
                "\n" +
                "总车位：4(个)\n" +
                "停车总量：3（辆）\n" +
                "总剩余车位：1（个）";

        //when
        when(lot1.getIdStr()).thenReturn("001");
        when(lot2.getIdStr()).thenReturn("002");
        when(lot1.getName()).thenReturn("软件园停车场");
        when(lot2.getName()).thenReturn("唐家湾停车场");
        when(lot1.getCapacity()).thenReturn(2);
        when(lot2.getCapacity()).thenReturn(2);
        when(lot1.getCarNum()).thenReturn(2);
        when(lot2.getCarNum()).thenReturn(1);

        //then
        String info=boy.getParkingLotsInfo();
        assertThat(info,is(except));
    }

    @Test
    public void should_add_a_parking_lot_when_call_buildParkingLot(){
        //give
        List<ParkingLot> lotList=new ArrayList<>();
        String name="唐家湾停车场";
        int capacity=2;
        ParkingBoy boy=new ParkingBoy(lotList);

        //when
        boy.buildParkingLot(name,capacity);

        //then
        assertThat(lotList.get(0).getName(),is(name));
        assertThat(lotList.get(0).getCapacity(),is(capacity));
    }

    @Test
    public void should_successfully_delete_a_empty_parking_lot_when_call_deleteParkingLot() {
        //give
        List<ParkingLot> lotList=new ArrayList<>();
        ParkingLot lot1=mock(ParkingLot.class);

        lotList.add(lot1);
        ParkingBoy boy=new ParkingBoy(lotList);

        //when
        when(lot1.hasCar()).thenReturn(false);
        boolean succee=boy.deleteParkingLot(lot1.getId());

        //then
        assertThat(succee,is(true));
        assertThat(lotList.contains(lot1),is(false));
    }

    @Test
    public void should_failly_delete_a_parking_lot_with_cars_when_call_deleteParkingLot(){
        //give
        List<ParkingLot> lotList=new ArrayList<>();
        ParkingLot lot1=mock(ParkingLot.class);

        lotList.add(lot1);
        ParkingBoy boy=new ParkingBoy(lotList);

        //when
        when(lot1.hasCar()).thenReturn(true);
        boolean succee=boy.deleteParkingLot(lot1.getId());

        //then
        assertThat(succee,is(false));
        assertThat(lotList.contains(lot1),is(true));
    }

    @Test
    public void should_failly_delete_a_parking_not_existent_when_call_deleteParkingLot(){
        //give
        List<ParkingLot> lotList=new ArrayList<>();
        ParkingLot lot1=mock(ParkingLot.class);

        lotList.add(lot1);
        ParkingBoy boy=new ParkingBoy(lotList);

        //when
        //then
        try {
            boolean succee=boy.deleteParkingLot(323232);
            fail("The Parking Lot Is Not Exit!");
        }catch (NotExitParkingLotException e){
            assertThat(lotList.contains(lot1),is(true));
        }


    }
}
