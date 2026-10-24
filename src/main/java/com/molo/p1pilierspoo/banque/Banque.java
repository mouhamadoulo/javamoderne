package com.molo.p1pilierspoo.banque;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Concepts vus ici : composition et gestion d'une collection d'objets.
 * Une banque gère plusieurs comptes
 * Elle peut manipuler des comptes courants et des comptes d'épargne au travers du type commun CompteBancaire
 * À retenir :
 * - List<CompteBancaire> permet de stocker différents types de comptes.
 * - Optional<CompteBancaire> représente un compte potentiellement absent.
 * - List.copyOf() renvoie une copie non modifiable de la liste, ce qui empêche le code appelant de modifier directement la collection interne.
 * - La banque détient ici la responsabilité de gérer la collection des comptes. Au sens strict, cette relation ressemble à une agrégation plutôt qu'à une composition forte, car les comptes peuvent exister indépendamment de la banque.
 */
public class Banque {

    private final String nom;
    private final List<CompteBancaire> comptes =
            new ArrayList<>();

    public Banque(String nom) {
        if (nom == null || nom.isBlank()) {
            throw new IllegalArgumentException(
                    "Nom de banque obligatoire"
            );
        }
        this.nom = nom;
    }

    public void ajouterCompte(CompteBancaire compte) {
        if (compte == null) {
            throw new IllegalArgumentException(
                    "Compte obligatoire"
            );
        }

        boolean existe = comptes.stream()
                .anyMatch(c -> c.getNumero()
                        .equals(compte.getNumero()));

        if (existe) {
            throw new IllegalArgumentException(
                    "Ce numéro de compte existe déjà"
            );
        }

        comptes.add(compte);
    }

    public Optional<CompteBancaire> rechercherCompte(
            String numero) {
        return comptes.stream()
                .filter(c -> c.getNumero().equals(numero))
                .findFirst();
    }

    public void afficherTousLesComptes() {
        System.out.println("\n=== Banque " + nom + " ===");

        for (CompteBancaire compte : comptes) {
            compte.afficherDetails();
            System.out.println("--------------------");
        }
    }

    public double calculerTotalSoldes() {
        return comptes.stream()
                .mapToDouble(CompteBancaire::getSolde)
                .sum();
    }

    public List<CompteBancaire> getComptes() {
        return List.copyOf(comptes);
    }
}
