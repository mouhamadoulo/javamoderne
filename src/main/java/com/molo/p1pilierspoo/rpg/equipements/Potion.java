package com.molo.p1pilierspoo.rpg.equipements;

/**
 * Objet immuable : tous les champs sont final, aucun setter.
 * (À l'étape suivante, on la transformera en record.)
 */
public final class Potion {

    private final String nom;
    private final int soin;

    public Potion(String nom, int soin) {
        if (soin <= 0) {
            throw new IllegalArgumentException("Une potion doit soigner au moins 1 PV");
        }
        this.nom = nom;
        this.soin = soin;
    }

    public String getNom() {
        return nom;
    }

    public int getSoin() {
        return soin;
    }

    @Override
    public String toString() {
        return nom + " (+" + soin + " PV)";
    }
}
