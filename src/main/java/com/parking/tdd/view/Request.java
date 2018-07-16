package com.parking.tdd.view;

import java.util.Scanner;

public class Request {
    private Scanner receiver=new Scanner(System.in);
    private String command;
    public Request(){}
    public Request(Scanner receiver) {
        this.receiver = receiver;
    }

    public String receive(){
        return receiver.nextLine();
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }
}
