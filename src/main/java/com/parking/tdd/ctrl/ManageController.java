package com.parking.tdd.ctrl;

import com.parking.tdd.core.ParkingBoy;
import com.parking.tdd.view.Request;
import com.parking.tdd.view.Response;

public class ManageController extends NomalController {
    private ParkingBoy boy;

    public ManageController(Request request, Response respons, ParkingBoy boy) {
        this.request = request;
        this.response = respons;
        this.boy = boy;
    }

    @Override
    public String process() {
        if (request.getCommand().equals("1")) {
            String info = boy.getParkingLotsInfo();
            response.send(info);
            return "forward:root";
        }else if(request.getCommand().equals("2")){
            response.send("请输入你套添加的停车场信息（格式为：名称，车位）：");
            return "";
        }else {
            response.send("请输入需要删除的被管理停车场ID: ");
            return "";
        }

    }
}

