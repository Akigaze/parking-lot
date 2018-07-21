package com.parking.tdd.ctrl;

import com.parking.tdd.view.Request;

import java.util.HashMap;
import java.util.Map;

public class Router {
    private Map<String,BasicController> controllerMap;
    private Request request;
    private String initPath="root";
    private String currentPath;

    public Router(HashMap<String, BasicController> controllerMap, Request request) {
        this.controllerMap=controllerMap;
        this.request=request;
    }

    public String getInitPath() {
        return this.initPath;
    }

    public void processRequest() {

    }

    public void initPage() {
        BasicController controller=controllerMap.get(initPath);
        controller.process();
    }
}
