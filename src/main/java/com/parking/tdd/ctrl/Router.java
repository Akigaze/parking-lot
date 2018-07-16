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
        controller.mainPage();

    }

    public String getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(String currentPage) {
        this.currentPage = currentPage;
    }

    public void setCurrentPageByCmd(String command) {
        if (command.equals(PARKCMD)) {
            this.currentPage = PARKPAGE;
        } else if (command.equals(UNPARKCMD)) {
            this.currentPage = UNPARKPAGE;
        }else if (command.equals(MAINPAGE)) {
            this.currentPage = MAINPAGE;
        } else {
            this.currentPage = ERRORPAGE;
        }
    }

    private void showPage(){
        switch (currentPage){
            case PARKPAGE:controller.parkingPage();break;
            case UNPARKPAGE:controller.unparkingPage();break;
            case MAINPAGE:controller.mainPage();break;
            default:controller.errorPage();break;
        }
    }

    private void handlePage(){
        if (currentPage.equals(PARKPAGE)) {
            controller.handleParking();
        } else if (currentPage.equals(UNPARKPAGE)) {
            controller.handleUnparking();
        }
    }

    public void nevigate(Request request) {
        setCurrentPageByCmd(request.getCommand());
        try {
            showPage();
            handlePage();
        }catch (AllParkingLotsFullException e){
        }finally {
            currentPage=MAINPAGE;
            controller.mainPage();
        }

    }

}
