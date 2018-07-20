package com.parking.tdd.ctrl;

import com.parking.tdd.view.Request;
import com.parking.tdd.view.Response;

public class GotoUnparkController extends NomalController{
    public GotoUnparkController(Response respons) {
        this.response=respons;
    }

    @Override
    public String process() {
        response.send("请输入小票编号：");
        return "";
    }
}
