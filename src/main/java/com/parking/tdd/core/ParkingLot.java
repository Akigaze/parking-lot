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
    private static int idCount=0;

    public ParkingLot(){}

    public ParkingLot(int capacity) {
        this.capacity = capacity;
    }

    public ParkingLot(String name, int capacity) {
        this.id = ++idCount;
        this.name = name;
        this.capacity = capacity;
    }

    public ParkingLot(int id, String name, int capacity) {
        this.id=id;
        this.name=name;
        this.capacity=capacity;
    }

    public static ParkingLot getInstance(int id){
        ParkingLot lot=new ParkingLot();
        lot.setId(id);
        return lot;
    }

    private void setId(int id) {
        this.id=id;
    }

    public Map<ParkingCard,Car> getParkedCars() {
        return this.parkedCars;
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

    public String getId() {
        return String.format("%03d",id);
    }

    public String getName() {
        return name;
    }

    public int getCapacity() {
        return capacity;
    }

    public int countCarNum() {
        return parkedCars.size();
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
}
