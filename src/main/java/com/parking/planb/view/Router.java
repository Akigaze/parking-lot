package com.parking.planb.view;

import com.parking.planb.ctrl.ParkingController;

public class Router {
    private final String PARKCMD="1";
    private final String UNPARKCMD="2";

    public static final String PARKPAGE="park";
    public static final String UNPARKPAGE="unpark";
    public static final String MAINPAGE="main";

    private String currentPage=MAINPAGE;
    private ParkingController controller;

    public Router(ParkingController controller) {
        this.controller = controller;

    }

    public String getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(String currentPage) {
        this.currentPage = currentPage;
    }

    public void setCurrentPageByCmd(String command) {
        if (command.equals(PARKCMD)){
            this.currentPage=PARKPAGE;
        }else if (command.equals(UNPARKCMD)){
            this.currentPage=UNPARKPAGE;
        }else {
            this.currentPage=command;
        }
    }

    public void nevigate(Request request) {
        setCurrentPageByCmd(request.getCommand());

    }
}
