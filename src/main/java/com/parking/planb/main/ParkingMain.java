package com.parking.planb.main;

import com.parking.planb.core.ParkingLot;
import com.parking.planb.ctrl.ParkingController;
import com.parking.planb.core.ParkingBoy;
import com.parking.planb.view.Request;
import com.parking.planb.view.Router;
import com.parking.planb.view.Viewer;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ParkingMain {
    public static void main(String[] args) {
        Viewer viewer=new Viewer();
        List<ParkingLot> lotList=new ArrayList<>();
        lotList.add(new ParkingLot(1));
        lotList.add(new ParkingLot(1));
        ParkingBoy boy=new ParkingBoy(lotList);
        ParkingController controller=new ParkingController(viewer,boy);
        controller.start();

        Request request=new Request();
        Scanner scanner=new Scanner(System.in);
        Router router=new Router(controller);

        while (true){
            String cmd=scanner.nextLine();
            request.setCommand(cmd);
            router.nevigate(request);
        }
    }
}
