package com.molo.p1pilierspoo.rpg.equipements;

public class Epee implements Arme {

    private final String nom;
    private final int degats;

    public Epee(String nom, int degats){
        if (degats <= 0) {
            throw new IllegalArgumentException("Une épée doit faire au moins 1 dégât");
        }
        this.nom = nom;
        this.degats = degats;
    }

    @Override
    public String nom() {
        return nom;
    }

    @Override
    public int degatsDeBase() {
        return degats;
    }
}
