package com.parking.plana.main;

import com.parking.plana.core.ParkingBoy;
import com.parking.plana.core.ParkingLot;
import com.parking.plana.ctrl.ParkingController;
import com.parking.plana.view.Viewer;

import java.util.ArrayList;
import java.util.List;

public class ParkingMain {
    public static void main(String[] args) {
        Viewer viewer=new Viewer();
        List<ParkingLot> lotList=new ArrayList<>();
        lotList.add(new ParkingLot(1));
        lotList.add(new ParkingLot(1));
        ParkingBoy boy=new ParkingBoy(lotList);
        ParkingController controller=new ParkingController(viewer,boy);
        controller.start();

    }
}
