package com.parking.tdd.core;

import com.parking.tdd.core.exception.ParkingLotFullException;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class ParkingLot {
    private int id;
    private String name;
    private int capacity;
    private Map<ParkingCard,Car> parkedCars=new HashMap<>();

    public ParkingLot(){}

    public ParkingLot(int capacity) {
        this.capacity = capacity;
    }

    public ParkingLot(String name, int capacity) {
        this((int)(Math.random()*100),name,capacity);
    }

    public ParkingLot(int id, String name, int capacity) {
        this.id=id;
        this.name=name;
        this.capacity=capacity;
    }

    public String getId() {
        return String.format("%03d",id);
    }

    public String getName() {
        return name;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getCarNum() {
        return parkedCars.size();
    }

    public ParkingCard park(Car car) {
        if (parkedCars.size()<capacity){
            ParkingCard card=new ParkingCard();
            parkedCars.put(card,car);
            return card;
        }else {
            throw new ParkingLotFullException();
        }
    }

    public Car unpark(ParkingCard card) {
        return parkedCars.remove(card);
    }

    public boolean isFull() {
        return parkedCars.size()==capacity;
    }

    public boolean containsParkingCard(ParkingCard card){
        return parkedCars.containsKey(card);
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ParkingLot lot = (ParkingLot) o;
        return id == lot.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }


    public boolean hasCar() {
        return getCarNum()>0;
    }
}
