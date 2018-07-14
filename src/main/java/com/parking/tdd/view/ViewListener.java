package com.parking.tdd.view;

import java.util.Scanner;

import static org.fusesource.jansi.Ansi.Color.*;
import static org.fusesource.jansi.Ansi.ansi;

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
        System.out.print( ansi().eraseScreen().fg(BLUE).a("1. 停车\n").fg(GREEN).a("2. 取车 \n") );
        System.out.println(ansi().eraseScreen().fg(BLACK).a("请输入您要进行的操作："));
    }
}
