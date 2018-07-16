package com.parking.tdd.view;

public class Router {
    private final String PARKCMD="1";
    private final String UNPARKCMD="2";

    public static final String PARKPAGE="park";
    public static final String UNPARKPAGE="unpark";
    public static final String MAINPAGE="main";

    private String currentPage=MAINPAGE;

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
}
