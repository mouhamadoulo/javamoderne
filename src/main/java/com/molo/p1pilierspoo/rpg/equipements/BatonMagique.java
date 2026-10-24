package com.molo.p1pilierspoo.rpg.equipements;

public class BatonMagique implements Arme {

    private final int puissance;

    public BatonMagique(int puissance) {
        this.puissance = puissance;
    }

    @Override
    public String nom() {
        return "Bâton magique";
    }

    @Override
    public int degatsDeBase() {
        return puissance;
    }
}
