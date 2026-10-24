package com.molo.p1bases.rpg.equipements;

public class Arc implements Arme {

    private final int degats;

    public Arc(int degats) {
        this.degats = degats;
    }

    @Override
    public String nom() {
        return "Arc long";
    }

    @Override
    public int degatsDeBase() {
        return degats;
    }

    @Override
    public String description() {
        return nom() + " (" + degats + " dgts, critique tous les 3 tirs)";
    }
}
