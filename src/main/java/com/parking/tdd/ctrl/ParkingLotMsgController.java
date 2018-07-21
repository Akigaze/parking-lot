package com.parking.tdd.ctrl;

import com.parking.tdd.core.ParkingBoy;
import com.parking.tdd.view.Request;
import com.parking.tdd.view.Response;

public class ParkingLotMsgController extends NomalController{
    private ParkingBoy boy;
    public ParkingLotMsgController(Request request, Response respons, ParkingBoy boy) {
        this.request=request;
        this.response=respons;
        this.boy=boy;
    }

    @Override
    public String process() {
        String info=boy.getParkingLotsInfo();
        response.send(info);
        return "forward:root";
    }
}
