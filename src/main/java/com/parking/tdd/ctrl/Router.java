package com.parking.tdd.ctrl;

import com.parking.tdd.view.Request;

import java.util.Map;

public class Router {
    private Map<String,BasicController> controllerMap;
    private Request request;
    private String initPath="root";
    private String currentPath;

    public Router(Map<String, BasicController> controllerMap, Request request) {
        this.controllerMap=controllerMap;
        this.request=request;
    }

    public String getInitPath() {
        return this.initPath;
    }

    public void setCurrentPathByRequset() {
        String cmd=request.getCommand();
        currentPath+="/"+cmd;
    }

    public void processRequest() {
        String cmd=request.getCommand();
        setCurrentPathByRequset();
        BasicController controller=controllerMap.get(currentPath);
        controller.process();
    }

    public void initPage() {
        BasicController controller=controllerMap.get(initPath);
        controller.process();
    }


    public void setCurrentPath(String path) {
        this.currentPath=path;
    }
}
