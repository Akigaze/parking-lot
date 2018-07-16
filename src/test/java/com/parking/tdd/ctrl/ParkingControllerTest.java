package com.parking.tdd.ctrl;

import com.parking.tdd.core.Car;
import com.parking.tdd.core.ParkingBoy;
import com.parking.tdd.core.ParkingCard;
import com.parking.tdd.core.exception.AllParkingLotsFullException;
import com.parking.tdd.core.exception.InvalidParkingCardException;
import com.parking.tdd.view.Request;
import com.parking.tdd.view.Response;
import org.junit.Test;

import java.util.UUID;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.*;

public class ParkingControllerTest {
    @Test
    public void should_show_mian_page_when_go_to_main_page() {
        //give
        ParkingBoy boy = mock(ParkingBoy.class);
        Request request=mock(Request.class);
        Response response=mock(Response.class);
        ParkingController controller = new ParkingController(boy,request,response);

        //when
        controller.mainPage();

        //then
        verify(response).showMainPage();
    }

    @Test
    public void should_show_error_page_when_go_to_error_page() {
        //give
        ParkingBoy boy = mock(ParkingBoy.class);
        Request request=mock(Request.class);
        Response response=mock(Response.class);
        ParkingController controller = new ParkingController(boy,request,response);

        //when
        controller.errorPage();

        //then
        verify(response).send("非法指令，请查证后再输");
    }

    @Test
    public void should_show_parking_page_when_ask_for_parking() {
        //give
        ParkingBoy boy = mock(ParkingBoy.class);
        Request request=mock(Request.class);
        Response response=mock(Response.class);
        ParkingController controller = new ParkingController(boy,request,response);

        //when
        when(boy.isAllParkingLotsFull()).thenReturn(false);
        controller.parkingPage();

        //then
        verify(boy).isAllParkingLotsFull();
        verify(response).send("请输入车牌号: ");
    }

    @Test
    public void should_show_error_parking_page_when_all_parking_lots_are_full() {
        //give
        ParkingBoy boy = mock(ParkingBoy.class);
        Request request=mock(Request.class);
        Response response=mock(Response.class);
        ParkingController controller = new ParkingController(boy,request,response);

        //when
        when(boy.isAllParkingLotsFull()).thenReturn(true);

        //then
        try {
            controller.parkingPage();
            fail("ALL PARKING LOTS ARE FULL!");
        }catch (AllParkingLotsFullException e){
            verify(boy).isAllParkingLotsFull();
            verify(response).send("车已停满，请晚点再来");
        }
    }
//
    @Test
    public void should_show_unparking_page_when_go_to_unparking_page() {
        //give
        ParkingBoy boy = mock(ParkingBoy.class);
        Request request=mock(Request.class);
        Response response=mock(Response.class);
        ParkingController controller = new ParkingController(boy,request,response);

        //when
        //then
        controller.unparkingPage();
        verify(response).send("请输入小票编号：");
    }

    @Test
    public void should_park_successfully_when_give_a_car_number() {
        //give
        ParkingBoy boy = mock(ParkingBoy.class);
        Request request=mock(Request.class);
        Response response=mock(Response.class);
        ParkingController controller = new ParkingController(boy,request,response);

        //when
        when(request.receive()).thenReturn("12345");
        when(boy.park(new Car("12345"))).thenReturn(new ParkingCard("12-34-56-78-45"));

        //then
        controller.handleParking();
        verify(response).send("停车成功，您的小票是：\n12-34-56-78-45");
    }
//
    @Test
    public void should_unpark_successfully_when_give_a_right_card_number() {
        //give
        ParkingBoy boy = mock(ParkingBoy.class);
        Request request=mock(Request.class);
        Response response=mock(Response.class);
        ParkingController controller = new ParkingController(boy,request,response);

        //when
        when(request.receive()).thenReturn("12-34-56-78-45");
        when(boy.unPark(new ParkingCard("12-34-56-78-45"))).thenReturn(new Car("12345"));

        //then
        controller.handleUnparking();
        verify(response).send("车已取出，您的车牌号是: 12345");
    }

    @Test
    public void should_unpark_failly_when_give_a_invalid_card_number() {
        //give
        ParkingBoy boy = mock(ParkingBoy.class);
        Request request=mock(Request.class);
        Response response=mock(Response.class);
        ParkingController controller = new ParkingController(boy,request,response);

        //when
        when(request.receive()).thenReturn("sdfdgfgjghjgj");
        when(boy.unPark(new ParkingCard("sdfdgfgjghjgj"))).thenThrow(new InvalidParkingCardException());

        //then
        controller.handleUnparking();
        verify(response).send("非法小票，无法取出车，请查证后再输");
    }
//
//    @Test
//    public void should_pick_specific_car_when_give_its_parking_card_number_from() {
//        //give
//        Viewer listener = mock(Viewer.class);
//        ParkingBoy boy = mock(ParkingBoy.class);
//        ParkingController controller = new ParkingController(listener, boy);
//        String cardId = UUID.randomUUID().toString();
//        Car car = new Car("1234");
//        ParkingCard card = new ParkingCard(cardId);
//
//        //when
//        when(listener.recept()).thenReturn("1","1234","2",cardId,"end");
//        when(boy.park(car)).thenReturn(card);
//
//        when(boy.isAllParkingLotsFull()).thenReturn(false);
//        when(boy.unPark(card)).thenReturn(car);
//
//        controller.start();
//
//        //then
//        verify(boy).park(car);
//        verify(boy).unPark(card);
//        verify(listener).send(String.format("停车成功，您的小票是：\n%s",cardId));
//        verify(listener).send("车已取出，您的车牌号是: 1234");
//
//    }
//
//    @Test
//    public void should_alarm_first_when_all_parking_lots_are_full_not_ask_for_a_car_number() {
//        //give
//        Viewer listener = mock(Viewer.class);
//        ParkingBoy boy = mock(ParkingBoy.class);
//        ParkingController controller = new ParkingController(listener, boy);
//
//        //when
//        when(listener.recept()).thenReturn("1","end");
//        when(boy.isAllParkingLotsFull()).thenReturn(true);
//
//        controller.start();
//
//        //then
//        verify(listener).send("车已停满，请晚点再来");
//
//    }
}
