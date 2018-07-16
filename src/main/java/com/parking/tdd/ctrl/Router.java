package com.parking.tdd.ctrl;

import com.parking.tdd.core.exception.AllParkingLotsFullException;
import com.parking.tdd.ctrl.ParkingController;
import com.parking.tdd.view.Request;

public class Router {
    private final String PARKCMD = "1";
    private final String UNPARKCMD = "2";

    public final String PARKPAGE = "park";
    public final String UNPARKPAGE = "unpark";
    public final String MAINPAGE = "main";
    public final String ERRORPAGE = "error";

    private String currentPage = MAINPAGE;
    private ParkingController controller;

    public Router(ParkingController controller) {
        this.controller = controller;
    }

    public void showPageByCmd(String cmd){
        switch (cmd){
            case PARKCMD:
                this.currentPage = PARKPAGE;
                controller.parkingPage();
                break;
            case UNPARKCMD:
                this.currentPage = UNPARKPAGE;
                controller.unparkingPage();
                break;
            default:
                this.currentPage = ERRORPAGE;
                controller.errorPage();
                break;
        }
    }

    public void handlePage(){
        if (currentPage.equals(PARKPAGE)) {
            controller.handleParking();
        } else if (currentPage.equals(UNPARKPAGE)) {
            controller.handleUnparking();
        }
    }

    public void nevigate(Request request) {
        String cmd=request.getCommand();
        try {
            showPageByCmd(cmd);
            handlePage();
        }catch (AllParkingLotsFullException e){
        }finally {
            controller.mainPage();
        }
    }

    public void setCurrentPage(String page) {
        this.currentPage=page;
    }
}
