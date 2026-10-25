package com.molo.p3collections;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Une List conserve l'ordre des éléments, accepte les doublons et permet l'accès par index.
 * À retenir :
 * - ArrayList : choix par défaut pour la plupart des listes.
 * - LinkedList : implémente aussi Deque, mais n'est pas automatiquement plus performante.
 * - get(index) permet l'accès par position.
 * - remove(index) supprime par position ; remove(object) supprime par valeur.
 */
public class DemoList {
    public static void main(String[] args) {
        List<String> noms = new ArrayList<>();

        noms.add("Ali");
        noms.add("Fatou");
        noms.add("Ali"); // Doublon autorisé

        System.out.println(noms);       // [Ali, Fatou, Ali]
        System.out.println(noms.get(0)); // Ali

        noms.set(1, "Aminata");         // Remplacer
        noms.remove("Ali");             // Supprimer la première occurrence
        System.out.println(noms);       // [Aminata, Ali]

        System.out.println(noms.contains("Ali")); // true

        // Autres opérations
        noms.add(0, "Moussa");
        System.out.println(noms.size());
        System.out.println(noms.isEmpty());

        // Parcours moderne
        noms.forEach(System.out::println);

        // LinkedList implémente également List
        List<String> villes = new LinkedList<>();
        villes.add("Paris");
        villes.add("Lyon");

        villes.forEach(System.out::println);
    }
}
