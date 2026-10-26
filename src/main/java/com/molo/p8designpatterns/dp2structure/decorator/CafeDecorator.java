package com.molo.p8designpatterns.dp2structure.decorator;

public abstract class CafeDecorator implements Cafe {

    protected final Cafe cafe;

    protected CafeDecorator(Cafe cafe) {
        this.cafe = cafe;
    }
}