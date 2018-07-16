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

    public void should_navigate_to_unparking_when_give_cammond_2() {
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
}
