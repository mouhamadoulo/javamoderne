package com.molo.p3collections;

import java.util.*;

/**
 * Trier avec Comparable et Comparator
 * Comparable définit l'ordre naturel d'un objet ;
 * Comparator permet de choisir différents critères de tri.
 */
public class DemoSorting {
    record Employe(String nom, int salaire)
            implements Comparable<Employe> {

        @Override
        public int compareTo(Employe autre) {
            return nom.compareTo(autre.nom);
        }
    }

    public static void main(String[] args) {
        List<Integer> nombres =
                new ArrayList<>(List.of(5, 1, 8, 2));

        Collections.sort(nombres);
        System.out.println(nombres); // [1, 2, 5, 8]

        nombres.sort(Comparator.reverseOrder());
        System.out.println(nombres); // [8, 5, 2, 1]

        List<Employe> employes = new ArrayList<>(List.of(
                new Employe("Ali", 40000),
                new Employe("Fatou", 50000),
                new Employe("Moussa", 40000)
        ));

        employes.sort(Comparator.comparingInt(
                Employe::salaire).reversed());

        System.out.println(employes);

        // Tri secondaire par nom
        employes.sort(
                Comparator.comparingInt(Employe::salaire)
                        .thenComparing(Employe::nom)
        );
    }
}

