package com.parking.tdd.ctrl;

import com.parking.tdd.core.ParkingBoy;
import com.parking.tdd.view.Request;
import com.parking.tdd.view.Response;

public class BuildParkingLotController extends NomalController {
    private ParkingBoy boy;

    public BuildParkingLotController(Request request, Response respons, ParkingBoy boy) {
        this.request = request;
        this.response = respons;
        this.boy = boy;
    }

    @Override
    public String process() {
        String lotMsg=request.getCommand().trim();
        if(lotMsg.indexOf("，")==-1){
            response.send("停车场信息有误，请确认后重新输入！");
        }else {
            String[] msgs=lotMsg.split("，");
            try {
                boolean result=boy.buildParkingLot(msgs[0],Integer.parseInt(msgs[1]));
                if (result){
                    response.send("停车场添加成功！");
                }
            }catch (Exception e){
                response.send("停车场车位信息有误，请确认后重新输入！");
            }
        }
        return "forward:root";

    }
}
