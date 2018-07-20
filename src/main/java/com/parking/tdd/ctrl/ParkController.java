package com.parking.tdd.ctrl;

import com.parking.tdd.core.Car;
import com.parking.tdd.core.ParkingBoy;
import com.parking.tdd.core.ParkingCard;
import com.parking.tdd.view.Request;
import com.parking.tdd.view.Response;

public class ParkController extends NomalController{
    private ParkingBoy boy;
    public ParkController(Request request, Response respons, ParkingBoy boy) {
        this.request=request;
        this.response=respons;
        this.boy=boy;
    }

    @Override
    public String process() {
        String carNum=request.getCommand();
        Transformer transformer=new Transformer();
        Car car=transformer.convertToCar(carNum);
        ParkingCard card=boy.park(car);
        response.send(String.format("停车成功，您的小票是：\n%s",card.getId()));
        return "forward:root";
    }
}
