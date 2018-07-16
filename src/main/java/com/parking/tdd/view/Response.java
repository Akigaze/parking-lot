package com.parking.tdd.view;

import static org.fusesource.jansi.Ansi.Color.BLACK;
import static org.fusesource.jansi.Ansi.Color.BLUE;
import static org.fusesource.jansi.Ansi.Color.GREEN;
import static org.fusesource.jansi.Ansi.ansi;

public class Response {
    public void send(String msg) {
        System.out.println(msg);
    }

    public void showMainPage() {
        System.out.print( ansi().eraseScreen().fg(BLUE).a("1. 停车\n").fg(GREEN).a("2. 取车 \n") );
        System.out.println(ansi().eraseScreen().fg(BLACK).a("请输入您要进行的操作："));
    }
}
