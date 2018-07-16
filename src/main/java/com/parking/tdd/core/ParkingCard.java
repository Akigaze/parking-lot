package com.parking.tdd.core;

import java.util.Objects;
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ParkingCard card = (ParkingCard) o;
        return Objects.equals(id, card.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
