package com.parking.tdd;

import com.parking.tdd.core.ParkingBoy;
import com.parking.tdd.core.ParkingLot;
import com.parking.tdd.ctrl.*;
import com.parking.tdd.view.Request;
import com.parking.tdd.view.Response;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner input=new Scanner(System.in);
        Request request=new Request();
        Response response=new Response();
        List<ParkingLot> lotList=new ArrayList<>();
        lotList.add(new ParkingLot(1,"软件园停车场",3));
        lotList.add(new ParkingLot(2,"唐家湾停车场",3));
        ParkingBoy boy=new ParkingBoy(lotList);
        Router router=new Router(request);
        initRouter(request,response,boy,router);
        router.initPage();

        while (true){
            String cmd=input.nextLine();
            request.setCommand(cmd);
            router.processRequest();
        }
    }

    public static void initRouter(Request request,Response response,ParkingBoy boy,Router router){
        BasicController root=new RootController(response);
        BasicController main=new MainController(request,response);
        BasicController gotoPark=new GotoParkController(request,response,boy);
        BasicController gotoUnpark=new GotoUnparkController(response);
        BasicController park=new ParkController(request,response,boy);
        BasicController unpark=new UnparkController(request,response,boy);
        BasicController manage=new ManageController(request,response,boy);
        BasicController build=new BuildParkingLotController(request,response,boy);
        BasicController delete=new DeleteParkingLotController(request,response,boy);
        BasicController error=new ErrorController(response);
        Map<String,BasicController> controllerMap=new HashMap<>();
        controllerMap.put("root",root);
        controllerMap.put("root/1",main);
        controllerMap.put("root/2",main);
        controllerMap.put("root/*",error);
        controllerMap.put("root/1/1",gotoPark);
        controllerMap.put("root/1/2",gotoUnpark);
        controllerMap.put("root/1/*",error);
        controllerMap.put("root/2/1",manage);
        controllerMap.put("root/2/2",manage);
        controllerMap.put("root/2/3",manage);
        controllerMap.put("root/2/*",error);
        controllerMap.put("root/1/1/*",park);
        controllerMap.put("root/1/2/*",unpark);
        controllerMap.put("root/2/2/*",build);
        controllerMap.put("root/2/3/*",delete);

        router.setControllerMap(controllerMap);
    }
}
