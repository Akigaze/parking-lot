package com.parking.tdd.ctrl;

import com.parking.tdd.view.Response;

public class RootController extends NomalController {

    public RootController(){}

    public RootController(Response response) {
        this.response = response;
    }

    @Override
    public String process(String requestPath) {
        String rootPage="1.停车服务\n2.停车场管理\n请输入您要进入的页面：";
        System.out.println(rootPage);
        return "";
    }
}
