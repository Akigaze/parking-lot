package com.parking.tdd.ctrl;

import com.parking.tdd.core.Car;
import com.parking.tdd.core.ParkingBoy;
import com.parking.tdd.core.ParkingCard;
import com.parking.tdd.view.ViewListener;
import org.junit.Test;

import java.util.UUID;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.fail;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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
}
