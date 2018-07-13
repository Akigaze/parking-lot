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

    public void start() {
        String msg=listener.recept();
        if (msg=="1"){
            String cardId=parking();
            listener.send(String.format("停车成功，您的小票是：\n %s",cardId));
        }else if (msg=="2"){
            String carId=picking();
            listener.send(String.format("车已取出，您的车牌号是: %s",carId));
        }
    }
}
