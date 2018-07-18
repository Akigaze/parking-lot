package com.parking.tdd.ctrl;

import com.parking.tdd.view.Response;

public class MainController extends NomalController{

    public MainController(Response response) {
        this.response=response;
    }

    @Override
    public String process(String requestPath) {
        String mainPage="1. 停车\n2. 取车\n请输入您要进行的操作：";
        response.send(mainPage);
        return "";
    }
}
