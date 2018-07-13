package com.parking.tdd.view;

import java.util.Scanner;

public class ViewListener {
    private Scanner scanner;

    public ViewListener() {
        this.scanner = new Scanner(System.in);
    }

    public String recept() {
        return scanner.next();
    }
    public void send(String msg){
        System.out.println(msg);
    }
    public void showMainPage(){
        String home="1. 停车\n2. 取车 \n请输入您要进行的操作：";
        System.out.println(home);
    }
}
