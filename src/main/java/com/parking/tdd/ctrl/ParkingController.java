package com.parking.tdd.ctrl;

import com.parking.tdd.core.Car;
import com.parking.tdd.core.ParkingBoy;
import com.parking.tdd.view.ViewListener;

public class ParkingController {
    private ViewListener listener;
    private ParkingBoy boy;
    public ParkingController(ViewListener listener, ParkingBoy boy) {
        this.listener=listener;
        this.boy=boy;
    }

    public void parking() {
        String id=listener.recept();
        Car car=new Transformer().convertToCar(id);
        boy.park(car);
    }
}
