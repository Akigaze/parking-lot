package com.parking.tdd.ctrl;

import com.parking.tdd.core.Car;
import com.parking.tdd.core.ParkingBoy;
import com.parking.tdd.view.ViewListener;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ParkingControllerTest {
    @Test
    public void should_park_a_car_when_give_a_car_number(){
        ViewListener listener=mock(ViewListener.class);
        ParkingBoy boy=mock(ParkingBoy.class);
        ParkingController controller=new ParkingController(listener,boy);

        when(listener.recept()).thenReturn("1234");

        controller.parking();
        verify(boy).park(new Car("1234"));
    }
}
