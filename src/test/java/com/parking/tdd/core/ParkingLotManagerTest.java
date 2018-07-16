package com.parking.tdd.core;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;

public class ParkingLotManagerTest {
    @Test
    public void should_show_message_of_only_one_parking_lot_when_call_getParkingLotsInfo(){
        //give
        ParkingBoy boy=mock(ParkingBoy.class);
        List<ParkingLot> lotList=new ArrayList<>();
        ParkingLot lot1=new ParkingLot("软件园停车场",2);
        lot1.park(new Car());
        lotList.add(lot1);
        ParkingLotManager manager=new ParkingLotManager(boy,lotList);
        String except="|停车场ID|名称|车位|已停车辆|剩余车位|\n" +
                "======================================\n" +
                "|001|软件园停车场|2(个)|1(辆)|1(个)|\n" +
                "\n" +
                "总车位：2(个)\n" +
                "停车总量：1（辆）\n" +
                "总剩余车位：1（个）";
        //when
        String info=manager.getParkingLotsInfo();
        //then
        assertThat(info,is(except));
    }

    @Test
    public void should_show_message_of_all_the_parking_lots_when_call_getParkingLotsInfo(){
        //give
        ParkingBoy boy=mock(ParkingBoy.class);
        List<ParkingLot> lotList=new ArrayList<>();
        ParkingLot lot1=new ParkingLot("软件园停车场",2);
        ParkingLot lot2=new ParkingLot("唐家湾停车场",2);

        lot1.park(new Car());
        lot2.park(new Car());

        lotList.add(lot1);
        lotList.add(lot2);

        ParkingLotManager manager=new ParkingLotManager(boy,lotList);
        String except="|停车场ID|名称|车位|已停车辆|剩余车位|\n" +
                "======================================\n" +
                "|002|软件园停车场|2(个)|1(辆)|1(个)|\n" +
                "|003|唐家湾停车场|2(个)|1(辆)|1(个)|\n" +
                "\n" +
                "总车位：4(个)\n" +
                "停车总量：2（辆）\n" +
                "总剩余车位：2（个）";
        //when
        String info=manager.getParkingLotsInfo();
        //then
        assertThat(info,is(except));
    }

    @Test
    public void should_add_a_parking_lot_when_call_buildParkingLot(){
        //give
        ParkingBoy boy=mock(ParkingBoy.class);
        List<ParkingLot> lotList=new ArrayList<>();
        ParkingLot lot1=new ParkingLot("软件园停车场",2);
        lot1.park(new Car());
        lotList.add(lot1);
        String name="唐家湾停车场";
        int capacity=2;
        ParkingLotManager manager=new ParkingLotManager(boy,lotList);

        //when
        manager.buildParkingLot(name,capacity);
        //then
        assertThat(lotList.get(1).getName(),is(name));
        assertThat(lotList.get(1).getCapacity(),is(capacity));

    }

    @Test
    public void should_successfully_delete_a_empty_parking_lot_when_call_deleteParkingLotById(){
        //give
        ParkingBoy boy=mock(ParkingBoy.class);
        List<ParkingLot> lotList=new ArrayList<>();
        ParkingLot lot1=new ParkingLot(1,"软件园停车场",2);
        ParkingLot lot2=new ParkingLot(2,"唐家湾停车场",2);

        lotList.add(lot1);
        lotList.add(lot2);
        ParkingLotManager manager=new ParkingLotManager(boy,lotList);

        //when
        manager.deleteParkingLotById(1);
        //then
        assertThat(lotList.contains(lot1),is(false));
        assertThat(lotList.contains(lot2),is(true));
    }
}
