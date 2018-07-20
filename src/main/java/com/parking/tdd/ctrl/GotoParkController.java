package com.parking.tdd.ctrl;

import com.parking.tdd.core.ParkingBoy;
import com.parking.tdd.view.Request;
import com.parking.tdd.view.Response;

public class GotoParkController extends NomalController{
    private ParkingBoy boy;
    public GotoParkController(Request request, Response respons, ParkingBoy boy) {
        this.request=request;
        this.response=respons;
        this.boy=boy;
    }

    @Override
    public String process() {
        if (!boy.isAllParkingLotsFull()){
            response.send("请输入车牌号: ");
            return "";
        }else{
            response.send("车已停满，请晚点再来");
            return "forward:root";
        }
    }
}
