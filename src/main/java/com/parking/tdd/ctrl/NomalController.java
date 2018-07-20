package com.parking.tdd.ctrl;

import com.parking.tdd.core.ParkingBoy;
import com.parking.tdd.view.Request;
import com.parking.tdd.view.Response;

public abstract class NomalController implements BasicController {
    protected Response response;
    protected Request request;
}
