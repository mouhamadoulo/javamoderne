package com.molo.p7latestconcepts;

import java.util.ArrayList;
import java.util.List;

/**
 * Method References et Sequenced Collections
 * Les method references rendent certaines lambdas plus concises.
 * Les Sequenced Collections, introduites en Java 21, fournissent des opérations standardisées pour les collections ayant un ordre de parcours.
 * Les quatre formes de Method References :
 * Forme	Exemple	Signification
 * Méthode statique	Integer::parseInt	Appeler une méthode statique
 * Méthode d'un objet précis	System.out::println	Utiliser une instance existante
 * Méthode d'une instance quelconque	String::toUpperCase	Appeler la méthode sur chaque élément
 * Constructeur	Produit::new	Créer un objet
 */
public class CollectionsDemo {

    public static void main(String[] args) {

        // 1. METHOD REFERENCES

        List<String> noms = List.of("Moussa", "Aminata", "Fatou");

        // Lambda classique
        noms.forEach(nom -> System.out.println(nom));

        // Équivalent avec method reference
        noms.forEach(System.out::println);

        // Référence à une méthode d'instance d'un objet
        noms.stream()
                .map(String::toUpperCase)
                .forEach(System.out::println);

        // Référence à un constructeur
        List<String> copie = noms.stream()
                .toList();

        // 2. SEQUENCED COLLECTIONS (Java 21+)

        List<String> prenoms = new ArrayList<>(
                List.of("Moussa", "Aminata", "Fatou")
        );

        System.out.println(prenoms.getFirst()); // Moussa
        System.out.println(prenoms.getLast());  // Fatou

        prenoms.addFirst("Ali");
        prenoms.addLast("Oumar");

        System.out.println(prenoms);

        System.out.println(prenoms.reversed());
    }
}

