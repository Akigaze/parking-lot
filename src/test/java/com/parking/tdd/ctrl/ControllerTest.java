package com.parking.tdd.ctrl;

import com.parking.tdd.core.Car;
import com.parking.tdd.core.ParkingBoy;
import com.parking.tdd.core.ParkingCard;
import com.parking.tdd.core.exception.AllParkingLotsFullException;
import com.parking.tdd.core.exception.InvalidParkingCardException;
import com.parking.tdd.view.Request;
import com.parking.tdd.view.Response;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.*;
import static org.hamcrest.MatcherAssert.assertThat;


public class ControllerTest {
    @Test
    public void should_show_parking_sevice_page_when_ask_path_root_1_to_MainController(){
        //give
        Response respons=mock(Response.class);
        Request request=mock(Request.class);
        MainController controller=new MainController(request,respons);

        //when
        when(request.getCommand()).thenReturn("1");
        controller.process();

        //then
        verify(respons).send("1. 停车\n2. 取车\n请输入您要进行的操作：");
    }

    @Test
    public void should_show_parking_lot_management_page_when_ask_path_root_2_to_MainController(){
        //give
        Response respons=mock(Response.class);
        Request request=mock(Request.class);
        MainController controller=new MainController(request,respons);

        //when
        when(request.getCommand()).thenReturn("2");
        controller.process();

        //then
        verify(respons).send("1. 查看停车场详情\n2. 添加停车场\n3. 删除停车场");
    }

    @Test
    public void should_into_parking_page_when_ask_path_root_1_1_to_GotoParkController(){
        //give
        Response respons=mock(Response.class);
        Request request=mock(Request.class);
        ParkingBoy boy=mock(ParkingBoy.class);
        GotoParkController controller=new GotoParkController(request,respons,boy);

        //when
        when(boy.isAllParkingLotsFull()).thenReturn(false);
        controller.process();

        //then
        verify(respons).send("请输入车牌号: ");
    }

    @Test
    public void should_show_lot_full_and_back_to_root_page_when_ask_path_root_1_1_to_GotoParkController(){
        //give
        Response respons=mock(Response.class);
        Request request=mock(Request.class);
        ParkingBoy boy=mock(ParkingBoy.class);
        GotoParkController controller=new GotoParkController(request,respons,boy);

        //when
        when(boy.isAllParkingLotsFull()).thenReturn(true);
        String forward=controller.process();

        //then
        assertThat(forward,is("forward:root"));
        verify(respons).send("车已停满，请晚点再来");
    }

    @Test
    public void should_into_unpark_page_when_ask_path_root_1_2_to_GotoUnparkController(){
        //give
        Response respons=mock(Response.class);
        Request request=mock(Request.class);
        GotoUnparkController controller=new GotoUnparkController(respons);

        //when
        String forward=controller.process();

        //then
        verify(respons).send("请输入小票编号：");
    }

    @Test
    public void should_show_error_msg_and_back_to_root_when_ask_a_wrong_path(){
        //give
        Response respons=mock(Response.class);
        ErrorController controller=new ErrorController(respons);

        //when
        String forward=controller.process();

        //then
        assertThat(forward,is("forward:root"));
        verify(respons).send("非法指令，请查证后再输");
    }

    @Test
    public void should_park_a_car_when_give_a_car_number_by_ParkController(){
        //give
        Response respons=mock(Response.class);
        Request request=mock(Request.class);
        ParkingBoy boy=mock(ParkingBoy.class);
        Car car=new Car("12345");
        ParkingCard card=new ParkingCard("000999888777");
        ParkController controller=new ParkController(request,respons,boy);

        //when
        when(request.getCommand()).thenReturn("12345");
        when(boy.park(car)).thenReturn(card);
        String forward=controller.process();

        //then
        assertThat(forward,is("forward:root"));
        verify(respons).send("停车成功，您的小票是：\n000999888777");
    }
}
