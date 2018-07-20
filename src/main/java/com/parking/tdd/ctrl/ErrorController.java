package com.parking.tdd.ctrl;

import com.parking.tdd.view.Response;

public class ErrorController extends NomalController{
    public ErrorController(Response respons) {
        this.response=respons;
    }

    @Override
    public String process() {
        response.send("非法指令，请查证后再输");
        return "forward:root";
    }
}
