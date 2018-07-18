package com.parking.tdd.ctrl;

import com.parking.tdd.core.Car;
import com.parking.tdd.core.ParkingBoy;
import com.parking.tdd.core.ParkingCard;
import com.parking.tdd.core.exception.AllParkingLotsFullException;
import com.parking.tdd.core.exception.InvalidParkingCardException;
import com.parking.tdd.view.Request;
import com.parking.tdd.view.Response;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.*;

public class ControllerTest {
    @Test
    public void should_show_parking_sevice_page_when_ask_path_root_1_to_MainController(){
        //give
        Response respons=mock(Response.class);
        MainController controller=new MainController(respons);
        String path="root/1";

        //when
        controller.process(path);

        //then
        verify(respons).send("1. 停车\n2. 取车\n请输入您要进行的操作：");
    }
}
