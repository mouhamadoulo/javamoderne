package com.molo.p1bases.rpg.personnages;

import com.molo.p1bases.rpg.equipements.Arme;

import java.util.List;

/**
 * Le boss est AUSSI un Personnage : le moteur de combat le manipule exactement
 * comme les héros, grâce au polymorphisme.
 */
public class Dragon extends Personnage {

    private int tour;

    public Dragon(String nom, Arme griffes) {
        super(nom, 420, griffes);
    }

    @Override
    protected int calculerDegats() {
        tour++;
        return getArme().degatsDeBase() + tour * 2;   // le dragon s'énerve au fil du combat
    }

    /** Attaque spéciale : tous les 3 tours, souffle de feu sur toute l'équipe. */
    public boolean doitCracherDuFeu() {
        return tour > 0 && tour % 3 == 0;
    }

    public void souffleDeFeu(List<? extends Personnage> cibles) {
        System.out.println("  🔥 " + getNom() + " crache du feu sur toute l'équipe !");
        for (Personnage cible : cibles) {
            if (cible.estVivant()) {
                cible.recevoirDegats(18);   // le Guerrier réduira ces dégâts grâce à son armure
            }
        }
    }
}
