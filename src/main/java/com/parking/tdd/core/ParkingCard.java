package com.parking.tdd.core;

import java.util.UUID;

public class ParkingCard {
    private String id;

    public ParkingCard() {
        this.id = UUID.randomUUID().toString();
    }
    public ParkingCard(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setID(String s) {
        this.id=s;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof ParkingCard){
            final ParkingCard card=(ParkingCard)obj;
            if (this.id.equals(card.id)){
                return true;
            }
        }
        return false;
    }
}
