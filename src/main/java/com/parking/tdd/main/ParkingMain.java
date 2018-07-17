package com.parking.tdd.main;

import com.parking.tdd.core.ParkingLot;
import com.parking.tdd.ctrl.ParkingController;
import com.parking.tdd.core.ParkingBoy;
import com.parking.tdd.view.Request;
import com.parking.tdd.view.Response;
import com.parking.tdd.ctrl.Router;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ParkingMain {
    public static void main(String[] args) {
        List<ParkingLot> lotList=new ArrayList<>();
        lotList.add(new ParkingLot(1));
        lotList.add(new ParkingLot(1));
        ParkingBoy boy=new ParkingBoy(lotList);
        Request request=new Request();
        Response response=new Response();
        ParkingController controller=new ParkingController(boy,request,response);

        Scanner scanner=new Scanner(System.in);
        Router router=new Router(controller);
        router.launch();
        while (true){
            String cmd=scanner.nextLine();
            request.setCommand(cmd);
            router.nevigate(request);
        }
    }
}
