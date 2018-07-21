package com.parking.tdd;

import com.parking.tdd.core.ParkingBoy;
import com.parking.tdd.core.ParkingLot;
import com.parking.tdd.view.Request;
import com.parking.tdd.view.Response;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Request request=new Request();
        Response response=new Response();
        List<ParkingLot> lotList=new ArrayList<>();
        ParkingBoy boy=new ParkingBoy(lotList);
    }
}
