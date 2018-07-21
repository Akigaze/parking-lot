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
        cmd=(cmd=="1"||cmd=="2") ? cmd : "*";
        currentPath+="/"+cmd;
    }

    public void processRequest() {
        setCurrentPathByRequset();
        BasicController controller=controllerMap.get(currentPath);
        String ward=controller.process();
        forward(ward);
    }
    private void forward(String ward){
        if (ward==""){
            return;
        }
        if (ward.contains("forward")){
            ward=ward.substring(ward.indexOf(":")+1);
        }
        controllerMap.get(ward).process();
        setCurrentPath(initPath);
    }

    public void initPage() {
        BasicController controller=controllerMap.get(initPath);
        controller.process();
    }


    public void setCurrentPath(String path) {
        this.currentPath=path;
    }
}
