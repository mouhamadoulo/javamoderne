package com.molo.p1pilierspoo.rpg.personnages;

import com.molo.p1pilierspoo.rpg.equipements.Arme;

public class Mage extends Personnage {

    private static final int COUT_SORT = 15;

    private int mana;   // encapsulé : seul le Mage modifie son mana

    public Mage(String nom, Arme arme) {
        super(nom, 70, arme);
        this.mana = 50;
    }

    @Override
    protected int calculerDegats() {
        if (mana >= COUT_SORT) {
            mana -= COUT_SORT;
            System.out.println("  " + getNom() + " lance une boule de feu ! (mana restant : " + mana + ")");
            return getArme().degatsDeBase() * 3;
        }
        System.out.println("  " + getNom() + " n'a plus de mana, coup de bâton...");
        return getArme().degatsDeBase() / 2;
    }

    public int getMana() {
        return mana;
    }

    /** On enrichit le toString du parent plutôt que de le réécrire entièrement. */
    @Override
    public String toString() {
        return super.toString() + " | mana " + mana;
    }
}
