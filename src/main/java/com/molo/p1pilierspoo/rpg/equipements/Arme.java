package com.molo.p1pilierspoo.rpg.equipements;

/**
 * ABSTRACTION (par interface) : on décrit CE QU'UNE ARME SAIT FAIRE,
 * sans dire COMMENT. Les personnages ne connaissent que ce contrat,
 * jamais la classe concrète (Epee, Arc, BatonMagique...).
 */
public interface Arme {

    String nom();

    int degatsDeBase();

    /** Méthode par défaut : un comportement commun, redéfinissable si besoin. */
    default String description() {
        return nom() + " (" + degatsDeBase() + " dgts)";
    }
}
