package com.parking.tdd.ctrl;

import com.parking.tdd.core.Car;
import com.parking.tdd.view.ViewListener;

public class Transformer {
    private ViewListener listener;
    public Transformer(ViewListener listner) {
        this.listener=listner;
    }

    public Car convertToCar() {
        String id=listener.recept();
        return new Car(id);
    }
}
