package com.molo.p1pilierspoo.rpg;

import com.molo.p1pilierspoo.rpg.equipements.*;
import com.molo.p1pilierspoo.rpg.personnages.*;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        // ---------- Création des héros ----------
        Guerrier conan = new Guerrier("Conan", new Epee("Épée rouillée", 10));
        Mage merlin    = new Mage("Merlin", new BatonMagique(9));
        Archer legolas = new Archer("Legolas", new Arc(14));

        // POLYMORPHISME : une seule liste de type Personnage, trois types réels différents
        List<Personnage> equipe = List.of(conan, merlin, legolas);

        // Composition : chaque héros a son propre inventaire
        for (Personnage heros : equipe) {
            heros.getInventaire().ajouter(new Potion("Petite potion", 25));
        }
        conan.getInventaire().ajouter(new Potion("Grande potion", 60));

        // Arme définie "à la volée" via une classe anonyme : preuve que seul le contrat compte
        Arme griffes = new Arme() {
            public String nom()        { return "Griffes"; }
            public int degatsDeBase()  { return 24; }
        };
        Dragon dragon = new Dragon("Smaug", griffes);

        afficherEtat(equipe, dragon);

        // Agrégation : on change l'arme de Conan sans toucher à sa classe
        conan.equiper(new Epee("Excalibur", 20));

        // ---------- Boucle de combat ----------
        int tour = 1;
        while (dragon.estVivant() && equipeEnVie(equipe)) {
            System.out.println("\n===== TOUR " + tour + " =====");

            for (Personnage heros : equipe) {
                if (!heros.estVivant()) continue;
                if (heros.getPv() < heros.getPvMax() * 0.3 && heros.boirePotion()) continue;
                heros.attaquer(dragon);   // ← même appel, trois comportements différents
                if (!dragon.estVivant()) break;
            }

            if (dragon.estVivant()) {
                Personnage cible = premierVivant(equipe);
                dragon.attaquer(cible);
                if (dragon.doitCracherDuFeu()) {
                    dragon.souffleDeFeu(equipe);
                }
            }

            afficherEtat(equipe, dragon);
            tour++;
        }

        System.out.println(dragon.estVivant()
                ? "\n💀 L'équipe a été vaincue..."
                : "\n🏆 " + dragon.getNom() + " est vaincu en " + (tour - 1) + " tours !");

        // ---------- Encapsulation : tentative de tricherie ----------
        try {
            conan.getInventaire().getPotions().clear();
        } catch (UnsupportedOperationException e) {
            System.out.println("\nImpossible de vider l'inventaire de l'extérieur : il est encapsulé.");
        }
    }

    private static boolean equipeEnVie(List<Personnage> equipe) {
        for (Personnage p : equipe) {
            if (p.estVivant()) return true;
        }
        return false;
    }

    private static Personnage premierVivant(List<Personnage> equipe) {
        for (Personnage p : equipe) {
            if (p.estVivant()) return p;
        }
        throw new IllegalStateException("Aucun héros vivant");
    }

    private static void afficherEtat(List<Personnage> equipe, Personnage boss) {
        System.out.println("  ---");
        for (Personnage p : equipe) {
            System.out.println("  " + p);   // toString() polymorphe
        }
        System.out.println("  " + boss);
    }
}

