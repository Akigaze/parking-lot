package com.parking.tdd.ctrl;

import com.parking.tdd.core.Car;
import com.parking.tdd.core.ParkingBoy;
import com.parking.tdd.core.ParkingCard;
import com.parking.tdd.core.exception.AllParkingLotsFullException;
import com.parking.tdd.core.exception.InvalidParkingCardException;
import com.parking.tdd.view.ViewListener;
import org.junit.Test;

import java.util.UUID;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.*;

public class ParkingControllerTest {
    @Test
    public void should_park_a_car_when_give_a_car_number() {
        //give
        ViewListener listener = mock(ViewListener.class);
        ParkingBoy boy = mock(ParkingBoy.class);
        ParkingController controller = new ParkingController(listener, boy);
        String cardId = UUID.randomUUID().toString();
        Car car = new Car("1234");
        ParkingCard card = new ParkingCard(cardId);

        //when
        when(listener.recept()).thenReturn("1234");
        when(boy.park(car)).thenReturn(card);
        String result = controller.parking();

        //then
        verify(boy).park(car);
        assertThat(result, is(cardId));
    }

    @Test
    public void should_pick_a_car_when_give_a_parking_card_number() {
        //give
        ViewListener listener = mock(ViewListener.class);
        ParkingBoy boy = mock(ParkingBoy.class);
        ParkingController controller = new ParkingController(listener, boy);
        String cardId = UUID.randomUUID().toString();
        Car car = new Car("1234");
        ParkingCard card = new ParkingCard(cardId);

        //when
        when(listener.recept()).thenReturn(cardId);
        when(boy.unPark(card)).thenReturn(car);
        String result=controller.picking();

        //then
        verify(boy).unPark(card);
        assertThat(result,is("1234"));
    }

    @Test
    public void should_park_a_car_when_select_1_and_then_give_a_car_number() {
        //give
        ViewListener listener = mock(ViewListener.class);
        ParkingBoy boy = mock(ParkingBoy.class);
        ParkingController controller = new ParkingController(listener, boy);
        String cardId = UUID.randomUUID().toString();
        Car car = new Car("1234");
        ParkingCard card = new ParkingCard(cardId);

        //when
        when(listener.recept()).thenReturn("1","1234","end");
        when(boy.park(car)).thenReturn(card);
        controller.start();

        //then
        verify(boy).park(car);
        verify(listener).send(String.format("停车成功，您的小票是：\n%s",cardId));
    }

    @Test
    public void should_pick_a_car_when_select_2_and_then_give_a_parking_card_number() {
        //give
        ViewListener listener = mock(ViewListener.class);
        ParkingBoy boy = mock(ParkingBoy.class);
        ParkingController controller = new ParkingController(listener, boy);
        String cardId = UUID.randomUUID().toString();
        Car car = new Car("1234");
        ParkingCard card = new ParkingCard(cardId);

        //when
        when(listener.recept()).thenReturn("2",cardId,"end");
        when(boy.unPark(card)).thenReturn(car);
        controller.start();

        //then
        verify(boy).unPark(card);
        verify(listener).send("车已取出，您的车牌号是: 1234");
    }

    @Test
    public void should_show_alarm_when__give_a_wrong_input_at_the_beginning() {
        //give
        ViewListener listener = mock(ViewListener.class);
        ParkingBoy boy = mock(ParkingBoy.class);
        ParkingController controller = new ParkingController(listener, boy);

        //when
        when(listener.recept()).thenReturn("sdfgs","end");
        controller.start();

        //then
        verify(listener).send("非法指令，请查证后再输");
    }

    @Test
    public void should_park_failly_when_all_parking_lots_are_full() {
        //give
        ViewListener listener = mock(ViewListener.class);
        ParkingBoy boy = mock(ParkingBoy.class);
        ParkingController controller = new ParkingController(listener, boy);
        Car car = new Car("1234");
        //when
        when(listener.recept()).thenReturn("1","1234","end");
        when(boy.park(car)).thenThrow(new AllParkingLotsFullException());

        //then
        controller.start();
        verify(listener).send("车已停满，请晚点再来");
    }

    @Test
    public void should_pick_a_car_failly_when_give_a_invalid_parking_card_number() {
        //give
        ViewListener listener = mock(ViewListener.class);
        ParkingBoy boy = mock(ParkingBoy.class);
        ParkingController controller = new ParkingController(listener, boy);
        String cardId = UUID.randomUUID().toString();
        ParkingCard card = new ParkingCard(cardId);

        //when
        when(listener.recept()).thenReturn("2",cardId,"end");
        when(boy.unPark(card)).thenThrow(new InvalidParkingCardException());
        controller.start();

        //then
        //verify(boy).unPark(card);
        verify(listener).send("非法小票，无法取出车，请查证后再输");
    }

    @Test
    public void should_pick_specific_car_when_give_its_parking_card_number_from() {
        //give
        ViewListener listener = mock(ViewListener.class);
        ParkingBoy boy = mock(ParkingBoy.class);
        ParkingController controller = new ParkingController(listener, boy);
        String cardId = UUID.randomUUID().toString();
        Car car = new Car("1234");
        ParkingCard card = new ParkingCard(cardId);

        //when
        when(listener.recept()).thenReturn("1","1234","2",cardId,"end");
        when(boy.park(car)).thenReturn(card);
        when(boy.unPark(card)).thenReturn(car);

        controller.start();

        //then
        verify(boy).park(car);
        verify(boy).unPark(card);
        verify(listener).send(String.format("停车成功，您的小票是：\n%s",cardId));
        verify(listener).send("车已取出，您的车牌号是: 1234");

    }

    @Test
    public void should_alarm_first_when_all_parking_lots_are_full_not_ask_for_a_car_number() {
        //give
        ViewListener listener = mock(ViewListener.class);
        ParkingBoy boy = mock(ParkingBoy.class);
        ParkingController controller = new ParkingController(listener, boy);

        //when
        when(listener.recept()).thenReturn("1","end");
        when(boy.isAllParkingLotsFull()).thenReturn(true);

        controller.start();

        //then
        verify(listener).send("车已停满，请晚点再来");

    }
}
