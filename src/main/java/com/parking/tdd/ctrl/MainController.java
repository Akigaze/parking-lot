package com.parking.tdd.ctrl;

import com.parking.tdd.view.Request;
import com.parking.tdd.view.Response;

public class MainController extends NomalController{

    public MainController(Request request,Response response) {
        this.request=request;
        this.response=response;
    }

    @Override
    public String process() {
        String mainPage=null;
        if (request.getCommand().equals("1")){
            mainPage="1. 停车\n2. 取车\n请输入您要进行的操作：";
        }else {
            mainPage="1. 查看停车场详情\n2. 添加停车场\n3. 删除停车场";
        }
        response.send(mainPage);
        return "";
    }
}
