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
}
