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

    @Test
    public void should_pick_a_car_when_give_a_right_parkingcard_number_by_UnoarkController(){
        //give
        Response respons=mock(Response.class);
        Request request=mock(Request.class);
        ParkingBoy boy=mock(ParkingBoy.class);
        Car car=new Car("12345");
        ParkingCard card=new ParkingCard("000999888777");
        UnoarkController controller=new UnoarkController(request,respons,boy);

        //when
        when(request.getCommand()).thenReturn("000999888777");
        when(boy.unPark(card)).thenReturn(car);
        String forward=controller.process();

        //then
        assertThat(forward,is("forward:root"));
        verify(respons).send("车已取出，您的车牌号是: 12345");
    }

    @Test
    public void should_show_error_msg_and_back_to_root_page_when_give_a_wrong_parkingcard_number_by_UnoarkController(){
        //give
        Response respons=mock(Response.class);
        Request request=mock(Request.class);
        ParkingBoy boy=mock(ParkingBoy.class);
        Car car=new Car("12345");
        ParkingCard card=new ParkingCard("000999888777");
        UnoarkController controller=new UnoarkController(request,respons,boy);

        //when
        when(request.getCommand()).thenReturn("000999888777");
        when(boy.unPark(card)).thenThrow(new InvalidParkingCardException());
        String forward=controller.process();

        //then
        assertThat(forward,is("forward:root"));
        verify(respons).send("非法小票，无法取出车，请查证后再输");
    }

    @Test
    public void should_show_parkinglot_msg_when_ask_root_2_1_to_ManageController(){
        //give
        Response respons=mock(Response.class);
        Request request=mock(Request.class);
        ParkingBoy boy=mock(ParkingBoy.class);
        ManageController controller=new ManageController(request,respons,boy);
        String info="|停车场ID|名称|车位|已停车辆|剩余车位|\n" +
                "======================================\n" +
                "|001|软件园停车场|2(个)|2(辆)|0(个)|\n" +
                "|002|唐家湾停车场|2(个)|1(辆)|1(个)|\n" +
                "\n" +
                "总车位：4(个)\n" +
                "停车总量：3（辆）\n" +
                "总剩余车位：1（个）";
        //when
        when(request.getCommand()).thenReturn("1");
        when(boy.getParkingLotsInfo()).thenReturn(info);
        String forward=controller.process();

        //then
        assertThat(forward,is("forward:root"));
        verify(respons).send(info);
    }

    @Test
    public void should_go_to_add_a_parking_lot_when_ask_root_2_2_to_ManageController(){
        //give
        Response respons=mock(Response.class);
        Request request=mock(Request.class);
        ParkingBoy boy=mock(ParkingBoy.class);
        ManageController controller=new ManageController(request,respons,boy);

        //when
        when(request.getCommand()).thenReturn("2");
        String forward=controller.process();

        //then
        verify(respons).send("请输入你套添加的停车场信息（格式为：名称，车位）：");
    }

    @Test
    public void should_go_to_delete_a_parking_lot_when_ask_root_2_3_to_ManageController(){
        //give
        Response respons=mock(Response.class);
        Request request=mock(Request.class);
        ParkingBoy boy=mock(ParkingBoy.class);
        ManageController controller=new ManageController(request,respons,boy);

        //when
        when(request.getCommand()).thenReturn("3");
        String forward=controller.process();

        //then
        verify(respons).send("请输入需要删除的被管理停车场ID: ");
    }
}
