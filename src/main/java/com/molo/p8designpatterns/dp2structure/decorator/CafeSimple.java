package com.molo.p8designpatterns.dp2structure.decorator;

public class CafeSimple implements Cafe {

    @Override
    public String description() {
        return "Café simple";
    }

    @Override
    public double prix() {
        return 2.00;
    }
}
