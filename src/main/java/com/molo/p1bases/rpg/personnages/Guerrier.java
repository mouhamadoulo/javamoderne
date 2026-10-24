package com.molo.p1bases.rpg.personnages;

import com.molo.p1bases.rpg.equipements.Arme;

/**
 * HÉRITAGE : un Guerrier EST UN Personnage. Il hérite de tout l'état et du comportement,
 * et spécialise : force, armure et rage.
 */
public class Guerrier extends Personnage {

    private final int force;
    private final int armure;

    public Guerrier(String nom, Arme arme) {
        super(nom, 120, arme);   // appel obligatoire au constructeur parent
        this.force = 8;
        this.armure = 4;
    }

    @Override
    protected int calculerDegats() {
        int degats = getArme().degatsDeBase() + force;
        if (estEnDanger()) {
            System.out.println("  " + getNom() + " entre en RAGE !");
            degats = degats * 3 / 2;
        }
        return degats;
    }

    /** Redéfinition qui RÉUTILISE le comportement parent via super. */
    @Override
    public void recevoirDegats(int degats) {
        int reduits = Math.max(1, degats - armure);
        super.recevoirDegats(reduits);
    }
}
