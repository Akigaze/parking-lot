package com.parking.tdd.ctrl;

import com.parking.tdd.core.Car;
import com.parking.tdd.core.ParkingBoy;
import com.parking.tdd.core.ParkingCard;
import com.parking.tdd.core.exception.InvalidParkingCardException;
import com.parking.tdd.view.Request;
import com.parking.tdd.view.Response;

public class UnoarkController extends NomalController{
    private ParkingBoy boy;
    public UnoarkController(Request request, Response respons, ParkingBoy boy) {
        this.request=request;
        this.response=respons;
        this.boy=boy;
    }

    @Override
    public String process() {
        String cardNum=request.getCommand();
        Transformer transformer=new Transformer();
        ParkingCard card=transformer.convertToParkingCard(cardNum);
        try{
            Car car=boy.unPark(card);
            response.send(String.format("车已取出，您的车牌号是: %s",car.getId()));
        }catch (InvalidParkingCardException e){
            response.send("非法小票，无法取出车，请查证后再输");
        }
        return "forward:root";
    }
}
