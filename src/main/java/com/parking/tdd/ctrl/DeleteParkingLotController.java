package com.parking.tdd.ctrl;

import com.parking.tdd.core.ParkingBoy;
import com.parking.tdd.core.ParkingLot;
import com.parking.tdd.core.exception.NotExitParkingLotException;
import com.parking.tdd.view.Request;
import com.parking.tdd.view.Response;

public class DeleteParkingLotController extends NomalController {
    private ParkingBoy boy;

    public DeleteParkingLotController(Request request, Response respons, ParkingBoy boy) {
        this.request = request;
        this.response = respons;
        this.boy = boy;
    }

    @Override
    public String process() {
        try {
            int id = Integer.parseInt(request.getCommand());
            boolean result = boy.deleteParkingLot(id);
            if (result) {
                response.send("停车场删除成功！");
            }else{
                response.send("此停车场中，依然停有汽车，无法删除！");
            }
        } catch (NotExitParkingLotException e) {
            response.send("此停车场不存在！");
        } catch (NumberFormatException e) {
            response.send("停车场ID信息有误，请确认后重新输入！");
        }

        return "forward:root";

    }
}
