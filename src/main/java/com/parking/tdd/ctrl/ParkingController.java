package com.parking.tdd.ctrl;

import com.parking.tdd.core.Car;
import com.parking.tdd.core.ParkingBoy;
import com.parking.tdd.core.ParkingCard;
import com.parking.tdd.core.exception.AllParkingLotsFullException;
import com.parking.tdd.core.exception.InvalidParkingCardException;
import com.parking.tdd.view.ViewListener;

public class ParkingController {
    private final String END="end";
    private final String PARK="1";
    private final String PICK="2";
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
    private void showMsgAfterMainPage(String msg){
        if (msg.equals(PARK)){
            if (boy.isAllParkingLotsFull())
                throw new AllParkingLotsFullException();
            else
                listener.send("请输入车牌号: ");
        }else if (msg.equals(PICK)){
            listener.send("请输入小票编号：");
        }else {
            listener.send("非法指令，请查证后再输");
        }
    }
    public void start() {
        while (true){
            listener.showMainPage();
            String msg=listener.recept();
            if (msg.equals(END))
                return;
            try {
                showMsgAfterMainPage(msg);
                if (msg.equals(PARK)){
                    String cardId=parking();
                    listener.send(String.format("停车成功，您的小票是：\n%s",cardId));
                }else if (msg.equals(PICK)){
                    String carId=picking();
                    listener.send(String.format("车已取出，您的车牌号是: %s",carId));
                }
            }catch (AllParkingLotsFullException exception){
                listener.send("车已停满，请晚点再来");
            }catch (InvalidParkingCardException exception){
                listener.send("非法小票，无法取出车，请查证后再输");
            }
        }
    }
}
