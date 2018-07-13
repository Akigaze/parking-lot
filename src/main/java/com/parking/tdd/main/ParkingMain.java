package com.parking.tdd.main;

import com.parking.tdd.core.ParkingBoy;
import com.parking.tdd.core.ParkingLot;
import com.parking.tdd.ctrl.ParkingController;
import com.parking.tdd.view.ViewListener;

import java.util.ArrayList;
import java.util.List;

public class ParkingMain {
    public static void main(String[] args) {
        ViewListener viewer=new ViewListener();
        List<ParkingLot> lotList=new ArrayList<>();
        lotList.add(new ParkingLot(1));
        lotList.add(new ParkingLot(1));
        ParkingBoy boy=new ParkingBoy(lotList);
        ParkingController controller=new ParkingController(viewer,boy);
        controller.start();
    }
}
