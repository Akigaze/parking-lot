package com.parking.tdd.ctrl;


import com.parking.tdd.view.Request;
import org.junit.Test;

import static org.mockito.Mockito.*;

public class RouterTest {
    @Test
    public void should_show_different_pages_when_give_different_commands() {
        //give
        ParkingController controller=mock(ParkingController.class);
        Router router=new Router(controller);

        //when
        router.showPageByCmd("1");
        //then
        verify(controller).parkingPage();

        //when
        router.showPageByCmd("2");
        //then
        verify(controller).unparkingPage();

        //when
        router.showPageByCmd("svsjvsj897");
        //then
        verify(controller).errorPage();
    }

    @Test
    public void should_go_to_different_pages_acording_to_currentPage() {
        //give
        ParkingController controller=mock(ParkingController.class);
        Router router=new Router(controller);

        //when
        router.setCurrentPage("unpark");
        router.handlePage();
        //then
        verify(controller).handleUnparking();

        //when
        router.setCurrentPage("park");
        router.handlePage();
        //then
        verify(controller).handleParking();
    }

    @Test
    public void should_navigate_to_parking_when_give_cammond_1() {
        //give
        ParkingController controller=mock(ParkingController.class);
        Request request=mock(Request.class);
        Router router=new Router(controller);

        //when
        when(request.getCommand()).thenReturn("1");
        router.nevigate(request);
        //then
        verify(controller).parkingPage();
        verify(controller).handleParking();
        verify(controller).mainPage();
    }
    @Test
    public void should_navigate_to_unparking_when_give_cammond_2() {
        //give
        ParkingController controller=mock(ParkingController.class);
        Request request=mock(Request.class);
        Router router=new Router(controller);

        //when
        when(request.getCommand()).thenReturn("2");
        router.nevigate(request);
        //then
        verify(controller).unparkingPage();
        verify(controller).handleUnparking();
        verify(controller).mainPage();
    }
    @Test
    public void should_show_alarm_when_give_a_error_cammond() {
        //give
        ParkingController controller=mock(ParkingController.class);
        Request request=mock(Request.class);
        Router router=new Router(controller);

        //when
        when(request.getCommand()).thenReturn("fgjkghkghj");
        router.nevigate(request);
        //then

        verify(controller).errorPage();
        verify(controller).mainPage();
    }
}
