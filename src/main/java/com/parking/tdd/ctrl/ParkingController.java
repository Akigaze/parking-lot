package com.parking.tdd.ctrl;

import com.parking.tdd.core.Car;
import com.parking.tdd.core.ParkingBoy;
import com.parking.tdd.core.ParkingCard;
import com.parking.tdd.core.exception.AllParkingLotsFullException;
import com.parking.tdd.core.exception.InvalidParkingCardException;
import com.parking.tdd.view.Request;
import com.parking.tdd.view.Response;

public class ParkingController {
    private ParkingBoy boy;
    private Request request;
    private Response response;
    Transformer transformer=new Transformer();

    public ParkingController(ParkingBoy boy, Request request, Response response) {
        this.boy = boy;
        this.request = request;
        this.response = response;
    }

    public void mainPage() {
        response.showMainPage();
    }

    public void errorPage() {
        response.send("非法指令，请查证后再输");
    }

    public void parkingPage() throws AllParkingLotsFullException{
        if (boy.isAllParkingLotsFull()){
            response.send("车已停满，请晚点再来");
            throw new AllParkingLotsFullException();
        }else {
            response.send("请输入车牌号: ");
        }
    }

    public void unparkingPage() {
        response.send("请输入小票编号：");
    }

    public void handleParking() {
        String id=request.receive();
        Car car=transformer.convertToCar(id);
        ParkingCard card=boy.park(car);
        String cardId= transformer.convertToParkingCardId(card);
        response.send(String.format("停车成功，您的小票是：\n%s",cardId));
    }

    public void handleUnparking() {
        String id=request.receive();
        ParkingCard card=transformer.convertToParkingCard(id);
        try{
            Car car=boy.unPark(card);
            String carId= transformer.convertToCarId(car);
            response.send(String.format("车已取出，您的车牌号是: %s",carId));
        }catch (InvalidParkingCardException e){
            response.send("非法小票，无法取出车，请查证后再输");
        }
    }
}
