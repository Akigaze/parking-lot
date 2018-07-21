package com.parking.tdd.ctrl;

import com.parking.tdd.view.Request;

import java.util.HashMap;
import java.util.Map;

public class Router {
    private Map<String,BasicController> controllerMap=new HashMap<>();
    private Request request;
    private String initPath="root";
    private String currentPath;

    public Router(Request request){
        this.request=request;
        currentPath=initPath;
    }

    public Router(Map<String, BasicController> controllerMap, Request request) {
        this(request);
        this.controllerMap=controllerMap;
    }

    public void setCurrentPathByRequset() {
        String cmd=request.getCommand();
        if (currentPath.equals("root/1")||currentPath.equals("root")){
            cmd=(cmd.equals("1")||cmd.equals("2")) ? cmd : "*";
        }else if(currentPath.equals("root/2")){
            cmd=(cmd.equals("1")||cmd.equals("2")||cmd.equals("3")) ? cmd : "*";
        }else {
            cmd="*";
        }
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

    public void setControllerMap(Map<String, BasicController> controllerMap) {
        this.controllerMap=controllerMap;
    }
}
