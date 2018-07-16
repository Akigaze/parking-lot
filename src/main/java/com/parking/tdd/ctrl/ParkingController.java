package com.parking.tdd.ctrl;

import com.parking.tdd.core.Car;
import com.parking.tdd.core.ParkingBoy;
import com.parking.tdd.core.ParkingCard;
import com.parking.tdd.core.exception.AllParkingLotsFullException;
import com.parking.tdd.core.exception.InvalidParkingCardException;
import com.parking.tdd.view.Router;
import com.parking.tdd.view.ViewListener;

public class ParkingController {
    private final String END="end";
    private final String PARK="1";
    private final String PICK="2";
    private ViewListener listener;
    private ParkingBoy boy;
    Transformer transformer=new Transformer();

    public ParkingController(ViewListener listener, ParkingBoy boy) {
        this.listener=listener;
        this.boy=boy;
    }

    public String parking() {
        String id=listener.recept();
        Car car=transformer.convertToCar(id);
        ParkingCard card=boy.park(car);
        return transformer.convertToParkingCardId(card);
    }

    public String picking() {
        String id=listener.recept();
        ParkingCard card=transformer.convertToParkingCard(id);
        return transformer.convertToCarId(boy.unPark(card));
    }

    private void handleUnParkingCommand() {
        listener.send("请输入小票编号：");
        String carId=picking();
        listener.send(String.format("车已取出，您的车牌号是: %s",carId));
    }

    private void handleParkingCommand() {
        if (boy.isAllParkingLotsFull())
            throw new AllParkingLotsFullException();
        else
            listener.send("请输入车牌号: ");
        String cardId=parking();
        listener.send(String.format("停车成功，您的小票是：\n%s",cardId));
    }

    public void start() {
        while (true){
            listener.showMainPage();
            String msg=listener.recept();
            try {
                if (msg.equals(END)) {
                    return;
                }else if (msg.equals(PARK)){
                    handleParkingCommand();
                }else if (msg.equals(PICK)){
                    handleUnParkingCommand();
                }else {
                    listener.send("非法指令，请查证后再输");
                }
            }catch (AllParkingLotsFullException exception){
                listener.send("车已停满，请晚点再来");
            }catch (InvalidParkingCardException exception){
                listener.send("非法小票，无法取出车，请查证后再输");
            }
        }
    }


    public void handlePage(Router router){
        String currentPage=router.getCurrentPage();
        if (currentPage.equals(Router.MAINPAGE)){
            handleMianPage();
        }else if(currentPage.equals(Router.PARKPAGE)){
            handleParkPage();
        }else if (currentPage.equals(Router.UNPARKPAGE)){
            handleUnparkPage();
        }else {
            listener.send("非法指令，请查证后再输");
        }
        router.setCurrentPageByCmd(Router.MAINPAGE);
    }

    private void handleUnparkPage() {
        try {
            picking();
        }catch (InvalidParkingCardException e){
            listener.send("非法小票，无法取出车，请查证后再输");
        }
    }

    private void handleParkPage() {
        if (boy.isAllParkingLotsFull()){
            listener.send("车已停满，请晚点再来");
        }else {
            listener.send("请输入车牌号: ");
            String id=parking();
        }
    }

    private void handleMianPage() {
        listener.showMainPage();
    }
}
