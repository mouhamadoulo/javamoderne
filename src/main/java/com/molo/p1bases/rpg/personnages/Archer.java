package com.molo.p1bases.rpg.personnages;

import com.molo.p1bases.rpg.equipements.Arme;

public class Archer extends Personnage {

    private int tirs;

    public Archer(String nom, Arme arme) {
        super(nom, 85, arme);
    }

    @Override
    protected int calculerDegats() {
        tirs++;
        if (tirs % 3 == 0) {
            System.out.println("  " + getNom() + " : TIR CRITIQUE !");
            return getArme().degatsDeBase() * 2;
        }
        return getArme().degatsDeBase();
    }
}
