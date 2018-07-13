package com.parking.tdd.ctrl;

import com.parking.tdd.core.Car;
import com.parking.tdd.core.ParkingBoy;
import com.parking.tdd.core.ParkingCard;
import com.parking.tdd.view.ViewListener;

public class ParkingController {
    private ViewListener listener;
    private ParkingBoy boy;
    public ParkingController(ViewListener listener, ParkingBoy boy) {
        this.listener=listener;
        this.boy=boy;
    }

    public String parking() {
        Transformer transformer=new Transformer();
        String id=listener.recept();
        Car car=transformer.convertToCar(id);
        return transformer.convertToParkingCardId(boy.park(car));

    }

    public String picking() {
        Transformer transformer=new Transformer();
        String id=listener.recept();
        ParkingCard card=transformer.convertToParkingCard(id);
        return transformer.convertToCarId(boy.unPark(card));
    }
}
